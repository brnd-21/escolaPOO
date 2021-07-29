package visao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import excecao.QtdUsuarios;
import media.tipoArquivo;
import dao.UsuarioDAO;
import dao.ConteudoDAO;
import dao.CursoDAO;
import dao.DisciplinaDAO;
import dao.GrupoDAO;
import dao.RelacaoDAO;
import modelo.Conteudo;
import modelo.Curso;
import modelo.Disciplina;
import modelo.Grupo;
import modelo.Relacao;
import modelo.Usuario;

public class Console {
	
	public static void main(String[] args) throws IOException {
		
		// Variáveis utilizadas para cadastro de usuários e leitura de dados
		Scanner leitor = new Scanner(System.in);
		
		int opcao, opcao_interna = 0, dados = 0, registros = 0;
		ResultSet rs;
		boolean verificador = true; // Usado para os loops de verificações
		
		try {
			UsuarioDAO userdao = new UsuarioDAO();
			ConteudoDAO condao = new ConteudoDAO();
			GrupoDAO grudao = new GrupoDAO();
			RelacaoDAO reladao = new RelacaoDAO();
			DisciplinaDAO disdao = new DisciplinaDAO();
			CursoDAO curdao = new CursoDAO();
			
			do {
				System.out.println("");
				System.out.println("| Selecione a área da operação:");
				System.out.println("| 1 - Usuários");
				System.out.println("| 2 - Conteúdo");
				System.out.println("| 3 - Grupos");
				System.out.println("| 4 - Relações");
				System.out.println("| 5 - Disciplinas");
				System.out.println("| 6 - Cursos");
				System.out.println("| 0 - Sair");
				System.out.println("---------");
				System.out.print("| : ");
				
				do {
					try {
						opcao = Integer.parseInt(leitor.nextLine());
					} catch (NumberFormatException a) {
						System.err.println("O valor informado não é um inteiro, tente novamente");
						opcao = -1;
					}
				}while(opcao < 0 && opcao > 6);
				
				System.out.println("");
				
				if(opcao != 0) {
					do {
						switch(opcao) {
							
							// USUÁRIO
						
							case 1:
								
								String nome, email, area;
								int prontuario;
								
								System.out.println("");
								System.out.println("| Selecione sua operação:");
								System.out.println("| 1 - Cadastrar Usuário");
								System.out.println("| 2 - Consultar Usuário");
								System.out.println("| 3 - Excluir um Usuário");
								System.out.println("| 4 - Listagem de todos os usuários");
								System.out.println("| 0 - Retornar");
								System.out.println("---------");
								
								verificador = true;
								
								do {
									try {
										System.out.print("| : ");
										opcao_interna = Integer.parseInt(leitor.nextLine());
										
										verificador = false;
									} catch (NumberFormatException a) {
										System.err.println("O valor informado não é um inteiro, tente novamente");
									}
								}while(verificador);
					
								System.out.println("");
								
								if (opcao_interna == 1) { // Inserção de usuários
									do {
										// Segundo menu para escolher se será o cadastro de um aluno ou de um professor
										System.out.println("");
										System.out.println("| Selecione a opção:");
										System.out.println("| 1 - Aluno");
										System.out.println("| 2 - Professor");
										System.out.println("| 0 - Retornar");
										System.out.println("---------");
										
										verificador = true;
										
										do {
											try {
												System.out.print("| : ");
												opcao_interna = Integer.parseInt(leitor.nextLine());
												
												verificador = false;
											} catch (NumberFormatException a) {
												System.err.println("O valor informado não é um inteiro, tente novamente");
											}
										}while(verificador);
										
										if(opcao_interna == 1 || opcao_interna == 2) {
											
											System.out.println("");
											System.out.print("Informe o nome: ");
											nome = leitor.nextLine();
											
											int confirma = 0;
											
											do {
												System.out.print("Informe o prontuário: ");
												
												try {
													verificador = true;
													
													prontuario = Integer.parseInt(leitor.nextLine());
													
													verificador = false;
												} catch (NumberFormatException a) {
													System.err.println("O prontuário precisa possuir apenas números, tente novamente");
													prontuario = 0;
												}
												confirma = UsuarioDAO.filtra(prontuario);
												
												if(confirma != 0)
													System.err.println("O Prontuário digitado já está inserido, informe um diferente");
											}while(confirma != 0 || verificador);
											
											Usuario usuario = new Usuario();
											usuario.setNome(nome);
											usuario.setProntuario(prontuario);
											
											System.out.print("Informe o email: ");
											email = leitor.nextLine();
											
											usuario.setEmail(email);
											
											if(opcao_interna == 2) { // Continua o registro para professor
												System.out.print("Informe a área de atuação: ");
												area = leitor.nextLine();
												
												usuario.setArea(area);
											}
											
											userdao = new UsuarioDAO();
											userdao.insere(usuario);	
											
											System.out.println("O Usuário foi cadastrado com sucesso!");
										}else if(opcao_interna != 0)
											System.err.println("Opção Inválida! Tente novamente.");
										
									}while(opcao_interna != 0);
	
								} else if (opcao_interna == 2) { // Consulta de usuário
								
									System.out.print("Informe um prontuário: ");
									
									verificador = true;
									
									do {
										try {
											prontuario = Integer.parseInt(leitor.nextLine());
											
											verificador = false;
										} catch (NumberFormatException a) {
											System.err.println("O tipo do prontuário informado é inválido, tente novamente.");
											prontuario = 0;
										}
									}while(verificador);
									
									ResultSet resultado = UsuarioDAO.consultaFiltrada(prontuario);
									
									if(resultado.next()) {
										
										nome = resultado.getString("nome");
										prontuario = resultado.getInt("prontuario");
										email = resultado.getString("email");
										area = resultado.getString("area");
										
										System.out.println();
										
										if(area != null)
											System.out.print("[Professor] -> ");
										else
											System.out.print("[Aluno] -> ");
										
										System.out.print("Nome: " + nome + ", Prontuário: "+ prontuario + ", Email: " + email);
										
										if(area != null)
											System.out.print(", Área: "+ area);
										
										System.out.println();
									}else
										System.err.println("Não há nenhum usuário com este prontuário");
								
								} else if (opcao_interna == 3) { // Exclusão de usuário
											
									ResultSet resultado = UsuarioDAO.consulta(); // Coletando os dados dos grupos
									
									ResultSet confirma = UsuarioDAO.consulta();
									
									dados = 0;
									
									while(confirma.next()) {
										dados += 1;
									}
									
									if(dados > 0) {
										try {
											System.out.println();
											System.out.println("Listando todos os usuários // ");
											while (resultado.next()) {
												nome = resultado.getString("nome");
												prontuario = resultado.getInt("prontuario");
												email = resultado.getString("email");
												area = resultado.getString("area");
												
												System.out.println();
												
												if(area != null)
													System.out.print("[Professor] -> ");
												else
													System.out.print("[Aluno] -> ");
												
												System.out.print("Nome: " + nome + ", Prontuário: "+ prontuario + ", Email: " + email);
												
												if(area != null)
													System.out.print(", Área: "+ area);
											}
											
											int validador = 0;
											System.out.println("");
											
											do {
												System.out.println("\nInforme o Prontuário que será removido, ");
												System.out.print("Digite 0 para retornar: ");	
												prontuario = Integer.parseInt(leitor.nextLine());	
												
												validador = UsuarioDAO.filtra(prontuario);
												if(prontuario == 0) { // Derruba o loop caso a opção seja de cancelar
													validador = 1;
													System.err.println("Operação cancelada, retornando");
												}
												
												if(validador == 0)
													System.err.println("O Prontuário não existe no banco, tente novamente");
												
											}while(validador == 0);
											System.out.println();
											
											if(prontuario != 0) {	
												System.out.println("1 - Confirmar Exclusão");
												System.out.println("2 - Cancelar Exclusão");
												System.out.print("| - : ");
												
												int confirma_exclusao = 0;
												
												verificador = true;
												
												do {
													try {
														confirma_exclusao = Integer.parseInt(leitor.nextLine());
														verificador = false;
													} catch (NumberFormatException a) {
														System.err.println("O valor informado não é um inteiro, tente novamente");
														confirma_exclusao = 0;
													}
												}while(verificador);
												
												if(confirma_exclusao == 1) {
													userdao = new UsuarioDAO();
													userdao.remove(prontuario);	
													
													// Apaga todos os itens de outras tabelas que possuem ligação com o usuário
													condao.exclusaoCadeiada(prontuario);
													disdao.exclusaoCadeiada(prontuario);
													reladao.exclusaoCadeiada(prontuario);
												}else
													System.err.println("Operação cancelada");
											}
										} catch (NumberFormatException a) {
											System.err.println("O valor informado não é um inteiro, tente novamente");
										}
									}else
										System.err.println("Não há Usuários registrados para poder excluir no momento");
									
								} else if (opcao_interna == 4) { // Listagem de usuários
									
									ResultSet resultado = UsuarioDAO.consulta();
									
									ResultSet confirma = UsuarioDAO.consulta();
									
									dados = 0;
										
									while(confirma.next()) {
										dados++;
									}
									
									try {
										if(dados > 0) {
											while (resultado.next()) {
												
												nome = resultado.getString("nome");
												prontuario = resultado.getInt("prontuario");
												email = resultado.getString("email");
												area = resultado.getString("area");
												
												System.out.println();
												
												if(area != null)
													System.out.print("[Professor] -> ");
												else
													System.out.print("[Aluno] -> ");
												
												System.out.print("Nome: "+ nome + ", Prontuário: " + prontuario + ", Email: " + email);
												
												if(area != null)
													System.out.print(", Área: "+ area);
											}
										}else
											System.err.println("Não há usuários registrados no sistema");
									} catch (SQLException e) {
										System.err.println("Não foi possível coletar os dados de usuários do banco de dados");
									}
								}
								
								System.out.println();
							break;
							
							// CONTEÚDO
							
							case 2:
								
								registros = 0;
								rs = UsuarioDAO.consulta();
								
								while(rs.next()) {
									registros ++;
								}
								
								if(registros > 0) {
									String conteudo, nome_content;
									int responsavel; // responsável == prontuário do usuário
									
									System.out.println("");
									System.out.println("| Selecione a opção:");
									System.out.println("| 1 - Cadastrar Conteudo");
									System.out.println("| 2 - Excluir Conteúdo");
									System.out.println("| 3 - Listar Conteúdos");
									System.out.println("| 0 - Retornar");
									System.out.println("---------");
									System.out.print("| : ");
									
									verificador = true;
									do {
										try {
											opcao_interna = Integer.parseInt(leitor.nextLine());
											verificador = false;
										} catch (NumberFormatException a) {
											System.err.print("Opção inválida, tente novamente");
										}
									}while(verificador);
									
									if(opcao_interna == 1) {
										
										String extensao;
										int confirma_extensao = 0;
										
										System.out.println("");
										System.out.print("Informe o nome: ");
										nome_content = leitor.nextLine();
										
										System.out.print("Insira o conteúdo: ");
										conteudo = leitor.nextLine();
										
										do {
											System.out.print("Informe o tipo do arquivo (txt, pdf, pptx ou docx): ");
											extensao = leitor.nextLine();
											
											if(extensao.equalsIgnoreCase("txt") || extensao.equalsIgnoreCase("pdf") || extensao.equalsIgnoreCase("pptx") || extensao.equalsIgnoreCase("docx"))
												confirma_extensao = 1;
										
										}while(confirma_extensao != 1);
										
										
										// ADAPTER
										tipoArquivo converte = new tipoArquivo(conteudo);
																				
										int confirma = 0;
										
										do {
											ResultSet user = UsuarioDAO.consulta();
											
											while(user.next()) {
												
												nome = user.getString("nome");
												prontuario = user.getInt("prontuario");
												email = user.getString("email");
												area = user.getString("area");
												
												System.out.println("");
												
												if(area != null)
													System.out.print("[Professor] -> ");
												else
													System.out.print("[Aluno] -> ");
												
												System.out.print("Nome: "+ nome + ", Prontuário: " + prontuario + ", Email: " + email);
												
												if(area != null)
													System.out.print(", Área: "+ area);
											}
											
											System.out.println("\n");
											
											verificador = true;
											
											do {
												try {
													System.out.print("Informe o prontuário do responsável pelo conteúdo: ");
													responsavel = Integer.parseInt(leitor.nextLine());
													
													confirma = UsuarioDAO.filtra(responsavel);
													
													if(confirma == 0)
														System.err.println("O Prontuário informado não existe, tente novamente");
														
													verificador = false;
												} catch (NumberFormatException a) {
													System.err.println("Informe um formato de prontuário válido");
													responsavel = 0;
												}
												}while(verificador);
										}while(confirma != 1);
										
										Conteudo content = new Conteudo();
										content.setNome(nome_content);
										content.setConteudo(conteudo);
										content.setResponsavel(responsavel);
										
										condao = new ConteudoDAO();
										condao.insere(content);
										
										converte.converteTipo(extensao, nome_content, content);
										
										System.out.println("O Conteúdo foi inserido com sucesso!");
									
									} else if (opcao_interna == 2) {
										int id;
										
										ResultSet resultado = condao.consulta(); // Coletando os dados dos conteúdos
										
										ResultSet confirma = condao.consulta();
										
										dados = 0;
										
										while(confirma.next()) {
											dados += 1;
										}
										
										if(dados > 0) {
											
											System.out.println();
											System.out.println("Listando todos os conteúdos // ");
											while (resultado.next()) {
												id = resultado.getInt("id_cont");
												nome = resultado.getString("titulo");
												responsavel = resultado.getInt("id_responsavel");
												
												System.out.println("ID: "+ id + ", Nome: " + nome + " Responsável: "+ responsavel);
											}
											
											try {
												int validador = 0;
												
												do {
													System.out.println();
													System.out.println("Informe o ID do Conteúdo que será removido, ");
													System.out.print("Digite 0 para retornar: ");	
													id = Integer.parseInt(leitor.nextLine());	
													
													validador = condao.filtra(id);
													if(id == 0) { // Derruba o loop caso a opção seja de cancelar
														validador = 1;
														System.err.println("Operação cancelada, retornando");
													}
													
													if(validador == 0)
														System.err.println("O ID não existe no banco, tente novamente");
													
												}while(validador == 0);
												System.out.println();
												
												if(id != 0) {
													System.out.println("1 - Confirmar Exclusão");
													System.out.println("2 - Cancelar Exclusão");
													System.out.print("| - : ");
													
													if(Integer.parseInt(leitor.nextLine()) == 1) {
														condao = new ConteudoDAO();
														condao.remove(id);
	
													}else 
														System.err.println("Operação cancelada");
												}
											} catch (NumberFormatException a) {
												System.err.println("O valor informado não está no formato correto");
											}
										}else
											System.err.println("Não há conteúdos registrados no momento para poder excluir");
									
									} else if(opcao_interna == 3) {
										ResultSet resultado = condao.consulta();
										
										ResultSet confirma = condao.consulta();
										
										dados = 0;
										
										while(confirma.next()) {
											dados += 1;
										}
										
										if(dados > 0) {
											System.out.println();
											System.out.println("Listando todos os conteúdos // ");
											
											try {
												while (resultado.next()) {
													nome = resultado.getString("titulo");
													conteudo = resultado.getString("conteudo");
													responsavel = resultado.getInt("id_responsavel");
													
													System.out.println("Nome: " + nome + ", Responsável: "+ responsavel + ", Conteúdo: "+ conteudo);
												}
											} catch (SQLException e) {
												System.err.println("Não foi possível coletar dados de conteúdos do banco");
											}
										}else
											System.err.println("Não há Conteúdos inseridos no momento para consultar");
									}
								}else
									System.err.println("Não há usuários registrados para poder acessar esta opção");
							break;
							
							// GRUPOS DE TRABALHO E PESQUISA
							
							case 3:
								
								registros = 0;
								rs = UsuarioDAO.consulta();
								
								while(rs.next()) {
									registros++;
								}
								
								if(registros > 1) {
									String tipoGrupo;
									int qtdGrupo;
									
									System.out.println("");
									System.out.println("| Selecione a opção:");
									System.out.println("| 1 - Cadastrar Grupo");
									System.out.println("| 2 - Excluir Grupo");
									System.out.println("| 3 - Listar Grupos");
									System.out.println("| 0 - Retornar");
									System.out.println("---------");
									System.out.print("| : ");
									
									verificador = true;
									do {
										try {
											opcao_interna = Integer.parseInt(leitor.nextLine());
											verificador = false;
										} catch (NumberFormatException a) {
											System.err.print("Opção inválida, tente novamente");
										}
									}while(verificador);
									
									if(opcao_interna == 1) {
										
										try {
											insereGrupo(registros);
										} catch(QtdUsuarios a) {
											System.err.println(a.getMessage());
										}
										
									} else if (opcao_interna == 2) {
										
										int id;
										
										ResultSet resultado = grudao.consulta(); // Coletando os dados dos grupos
										
										ResultSet confirma = grudao.consulta();
										
										dados = 0;
										
										while(confirma.next()) {
											dados += 1;
										}
										
										if(dados > 0) {
											
											System.out.println();
											System.out.println("Listando todos os grupos // ");
											
											while (resultado.next()) {
												id = resultado.getInt("id_grupo");
												nome = resultado.getString("nome");
												tipoGrupo = resultado.getString("tipo_grupo");
												qtdGrupo = resultado.getInt("qtd_alunos");
												
												System.out.println("ID: "+ id + ", Nome: " + nome + ", Tipo: "+ tipoGrupo + ", Membros: "+ qtdGrupo);
											}
											
											verificador = true;
											
											do {
												try {
													int validador = 0;
													
													do {
														System.out.println();
														System.out.println("Informe o ID do grupo que será removido, ");
														System.out.print("Digite 0 para retornar: ");	
														id = Integer.parseInt(leitor.nextLine());	
														
														validador = grudao.filtra(id);
														if(id == 0) { // Derruba o loop caso a opção seja de cancelar
															validador = 1;
															System.err.println("Operação cancelada, retornando");
														}
														
														if(validador == 0)
															System.err.println("O ID não existe no banco, tente novamente");
														
													}while(validador == 0);
													System.out.println();
													
													if(id != 0) {
														System.out.println("1 - Confirmar Exclusão");
														System.out.println("2 - Cancelar Exclusão");
														System.out.print("| - : ");
														
														if(Integer.parseInt(leitor.nextLine()) == 1) {
															grudao = new GrupoDAO();
															grudao.remove(id);
															
														}else
															System.err.println("Operação cancelada");
													}
													
													verificador = false;
												} catch (NumberFormatException a) {
													System.err.println("O tipo de valor inserido está incorreto, tente novamente");
												}
											}while(verificador);
										}else
											System.err.println("Não há grupos registrados para poder excluir no momento");
		
									} else if (opcao_interna == 3) {
										ResultSet resultado = grudao.consulta();
										
										ResultSet confirma = grudao.consulta();
										
										dados = 0;
										
										while(confirma.next()) {
											dados += 1;
										}
										
										if(dados > 0) {
											System.out.println();
											System.out.println("Listando todos os grupos // ");
											
											try {
												while (resultado.next()) {
													nome = resultado.getString("nome");
													tipoGrupo = resultado.getString("tipo_grupo");
													qtdGrupo = resultado.getInt("qtd_alunos");
													
													System.out.println("Nome: " + nome + ", Tipo: "+ tipoGrupo + ", Membros: "+ qtdGrupo);
												}
											} catch (SQLException e) {
												System.err.println("Não foi possível buscar os dados dos grupos no banco");
											}
										}else
											System.err.println("Não há grupos cadastrados no momento para consultar");
									}
								}else
									System.err.println("Não há usuários suficientes registrados para poder acessar esta opção");
							break;
							
							// RELACIONAMENTOS
							
							case 4:
								
								registros = 0;
								rs = UsuarioDAO.consulta();
								
								while(rs.next()) {
									registros ++;
								}
								
								if(registros > 1) {
									String status;
									int pessoa_1, pessoa_2, checa = 0;
									boolean confirma_tipo = true;
									
									System.out.println("");
									System.out.println("| Selecione a opção:");
									System.out.println("| 1 - Novo relacionamento");
									System.out.println("| 2 - Remover relacionamento");
									System.out.println("| 3 - Listar relacionamentos");
									System.out.println("| 4 - Listar usuários por qtd de relacionamentos");
									System.out.println("| 0 - Retornar");
									System.out.println("---------");
									System.out.print("| : ");
									
									verificador = true;
									do {
										try {
											opcao_interna = Integer.parseInt(leitor.nextLine());
											verificador = false;
										} catch (NumberFormatException a) {
											System.err.print("Opção inválida, tente novamente");
										}
									}while(verificador);
									
									if(opcao_interna == 1) {
										
										// Inserção de relacionamento
										System.out.println("");
										do {
											try {
												verificador = true;
												
												ResultSet user = UsuarioDAO.consulta();
												
												while(user.next()) {
													
													nome = user.getString("nome");
													prontuario = user.getInt("prontuario");
													email = user.getString("email");
													area = user.getString("area");
													
													System.out.println("");
													
													if(area != null)
														System.out.print("[Professor] -> ");
													else
														System.out.print("[Aluno] -> ");
													
													System.out.print("Nome: "+ nome + ", Prontuário: " + prontuario + ", Email: " + email);
													
													if(area != null)
														System.out.print(", Área: "+ area);
												}
												System.out.println();
												
												do {
													System.out.println();
													System.out.print("Informe o prontuário do 1° usuário: ");
													pessoa_1 = Integer.parseInt(leitor.nextLine());
												
													checa = UsuarioDAO.filtra(pessoa_1); // Confirma se a pessoa existe no banco
												
													if(checa == 0)
														System.err.println("Este prontuário não existe, tente novamente");
												}while(checa != 1);
												
												checa = 0;
												
												ResultSet user2 = UsuarioDAO.consultaGrupos(pessoa_1);
												
												while(user2.next()) {
													
													nome = user2.getString("nome");
													prontuario = user2.getInt("prontuario");
													email = user2.getString("email");
													area = user2.getString("area");
													
													System.out.println("");
													
													if(area != null)
														System.out.print("[Professor] -> ");
													else
														System.out.print("[Aluno] -> ");
													
													System.out.print("Nome: "+ nome + ", Prontuário: " + prontuario + ", Email: " + email);
													
													if(area != null)
														System.out.print(", Área: "+ area);
												}
												
												do {
													System.out.println("\n");
													System.out.print("Informe o prontuário do 2° usuário: ");
													pessoa_2 = Integer.parseInt(leitor.nextLine());
													
													checa = UsuarioDAO.filtra(pessoa_2); // Confirma se a pessoa existe no banco
													
													if(checa == 0)
														System.err.println("Este prontuário não existe, tente novamente");
														
													if(pessoa_1 == pessoa_2) { // Confirma que a relação não repete os usuários
														System.err.println("Informe um prontuário de uma pessoa diferente da 1° informada");
														checa = 0;
													}
													
												}while(checa != 1);
												
												verificador = false;
											}catch (NumberFormatException a) {
												System.err.println("O tipo de valor inserido está incorreto, tente novamente");
												pessoa_1 = 0;
												pessoa_2 = 0;
											}
										}while(verificador);
										
										System.out.println();
										System.out.print("Informe o tipo de relacionamento,\n");
										System.out.print("Escolha entre, Publico ou Privado: ");
										
										do {
											status = leitor.nextLine().toLowerCase();
											
											// Valida se o tipo de grupo está correto
											if("publico".equals(status) || "privado".equals(status))
												confirma_tipo = false;
											else {
												confirma_tipo = true;
												System.err.println("Informe entre Publico ou Privado");
											}
										}while(confirma_tipo);
										
										Relacao relacao = new Relacao();
										relacao.setPessoa1(pessoa_1);
										relacao.setPessoa2(pessoa_2);
										relacao.setStatus(status);
										
										reladao = new RelacaoDAO();
										reladao.insere(relacao);
										
										System.out.println("A Relação foi inserida com sucesso!");
										
									} else if(opcao_interna == 2) {
										
										int id;
										
										ResultSet resultado = reladao.consulta(); // Coletando os dados das relações
										
										ResultSet confirma = reladao.consulta();
										
										dados = 0;
										
										while(confirma.next()) {
											dados += 1;
										}
										
										if(dados > 0) {
											
											System.out.println();
											System.out.println("Listando todos as relações // ");
											while (resultado.next()) {
												id = resultado.getInt("id_relacao");
												pessoa_1 = resultado.getInt("id_user1");
												pessoa_2 = resultado.getInt("id_user2");
												status = resultado.getString("tipo_relacao");
												
												
												System.out.println("ID: "+ id + ", Pessoa 1: " + pessoa_1 + ", Pessoa 2: "+ pessoa_2 + ", Tipo: "+ status);
											}
											
											verificador = true;
											do {
												try {
													int validador = 0;
													
													do {
														System.out.println();
														System.out.println("Informe o ID da relação que será removida, ");
														System.out.print("Digite 0 para retornar: ");	
														id = Integer.parseInt(leitor.nextLine());	
														
														validador = reladao.filtra(id);
														if(id == 0) { // Derruba o loop caso a opção seja de cancelar
															validador = 1;
															System.err.println("Operação cancelada, retornando");
														}
														
														if(validador == 0)
															System.err.println("O ID não existe no banco, tente novamente");
														
													}while(validador == 0);
													System.out.println();
													
													if(id != 0) {
														System.out.println("1 - Confirmar Exclusão");
														System.out.println("2 - Cancelar Exclusão");
														System.out.print("| - : ");
														
														if(Integer.parseInt(leitor.nextLine()) == 1) {
															reladao = new RelacaoDAO();
															reladao.remove(id);
															
														}else
															System.err.println("Operação cancelada");
													}
													
													verificador = false;
												} catch (NumberFormatException a) {
													System.err.println("O Valor digitado está em formato incorreto, tente novamente");
												}
											}while(verificador);
										}else
											System.err.println("Não há relações inseridas para poder excluir no momento");
										
									} else if (opcao_interna == 3) {
										ResultSet resultado = reladao.consulta();
										
										ResultSet confirma = reladao.consulta();
										
										dados = 0;
										
										while(confirma.next()) {
											dados += 1;
										}
										
										if(dados > 0) {
											System.out.println();
											System.out.println("Listando todas as relações // ");
											
											try {
												while (resultado.next()) {
													pessoa_1 = resultado.getInt("id_user1");
													pessoa_2 = resultado.getInt("id_user2");
													status = resultado.getString("tipo_relacao");
													
													System.out.println("Pessoa 1: " + pessoa_1 + ", Pessoa 2: "+ pessoa_2 + ", Tipo: "+ status);
												}
											} catch (SQLException e) {
												System.err.println("Não foi possível buscar os dados das relações no banco");
											}
										}else
											System.err.println("Não há relações feitas no momento para consultar");
									
									} else if (opcao_interna == 4) {
										
										ResultSet resultado = reladao.consultaFiltrada();
										
										ResultSet confirma = reladao.consultaFiltrada();
										
										dados = 0;
										int quantidade = 0;
										
										while(confirma.next()) {
											dados += 1;
										}
										
										if(dados > 0) {
											System.out.println();
											System.out.println("Listando todas as relações por quantidade // ");
											
											try {
												while (resultado.next()) {
													pessoa_1 = resultado.getInt("id_user1");
													quantidade = resultado.getInt("relacoes");
													
													System.out.println("Prontuário: " + pessoa_1 + ", Relações ativas: "+ quantidade);
												}
											} catch (SQLException e) {
												System.err.println("Não foi possível buscar os dados das relações no banco");
											}
										}else
											System.err.println("Não há relações feitas no momento para consultar");
									}
								}else
									System.err.println("Não há usuários registrados para poder acessar esta opção");
							break;
							
							// DISCIPLINA
							
							case 5:
								
								registros = 0;
								rs = UsuarioDAO.consultaProfessor();
								
								while(rs.next()) {
									registros++;
								}
								
								if(registros > 0) {
									String nomeDisciplina;
									int professorRespons;
									
									System.out.println();
									System.out.println("| Selecione a opção:");
									System.out.println("| 1 - Cadastrar Disciplina");
									System.out.println("| 2 - Excluir Disciplina");
									System.out.println("| 3 - Listar Disciplinas");
									System.out.println("| 0 - Retornar");
									System.out.println("---------");
									System.out.print("| : ");
									
									verificador = true;
									do {
										try {
											opcao_interna = Integer.parseInt(leitor.nextLine());
											verificador = false;
										} catch (NumberFormatException a) {
											System.err.print("Opção inválida, tente novamente");
										}
									}while(verificador);
									
									int checa = 0;
									
									if(opcao_interna == 1) {
																		
										// Inserção de disciplina
										System.out.println("");
										System.out.print("Informe o nome da Disciplina: ");
										nomeDisciplina = leitor.nextLine();
										
										do {
											ResultSet user = UsuarioDAO.consultaProfessor();
											
											while(user.next()) {
												
												nome = user.getString("nome");
												prontuario = user.getInt("prontuario");
												email = user.getString("email");
												area = user.getString("area");
												
												System.out.println("");
												System.out.print("[Professor] -> " + nome + " :: " + prontuario + " :: " + email + " :: "+ area);
											}
											
											System.out.println("\n");
											
											System.out.print("Informe o Prontuário do Professor responsável: ");
											
											verificador = true;
											do {
												try {
													professorRespons = Integer.parseInt(leitor.nextLine());
													verificador = false;
												} catch (NumberFormatException a) {
													System.err.println("O Prontuário do professor está em formato incorreto, tente novamente");
													professorRespons = 0;
												}
											}while(verificador);
											
											checa = UsuarioDAO.filtra(professorRespons);
										}while(checa != 1);
										
										Disciplina discip = new Disciplina(nomeDisciplina, professorRespons);
										// discip.setNome(nomeDisciplina);
										// discip.setProfessorResp(professorRespons);
										
										disdao = new DisciplinaDAO();
										disdao.insere(discip);
									
										System.out.println("A Disciplina foi inserida com sucesso!");
										
									} else if(opcao_interna == 2) {
										int id;
										
										ResultSet resultado = disdao.consulta(); // Coletando os dados das disciplinas
										
										ResultSet confirma = disdao.consulta();
										
										dados = 0;
										
										while(confirma.next()) {
											dados += 1;
										}
										
										if(dados > 0) {
											
											System.out.println();
											System.out.println("Listando todas as disciplinas // ");
											while (resultado.next()) {
												id = resultado.getInt("id_disc");
												nome = resultado.getString("nome");
												professorRespons = resultado.getInt("id_professor");
												
												System.out.println("ID: "+ id + ", Nome: " + nome + ", Professor Responsável: "+ professorRespons);
											}
											
											verificador = true;
											
											do { // Irá repetir o processo até que o valor informado seja um inteiro
												try {
													int validador = 0;
													
													do {
														System.out.println();
														System.out.println("Informe o ID da Disciplina que será removida, ");
														System.out.print("Digite 0 para retornar: ");	
														id = Integer.parseInt(leitor.nextLine());	
														
														validador = disdao.filtra(id);
														if(id == 0) { // Derruba o loop caso a opção seja de cancelar
															validador = 1;
															System.err.println("Operação cancelada, retornando");
														}
														
														if(validador == 0)
															System.err.println("O ID não existe no banco, tente novamente");
														
													}while(validador == 0);
													System.out.println();
													
													if(id != 0) {
														System.out.println("1 - Confirmar Exclusão");
														System.out.println("2 - Cancelar Exclusão");
														System.out.print("| - : ");
														
														if(Integer.parseInt(leitor.nextLine()) == 1) {
															disdao = new DisciplinaDAO();
															disdao.remove(id);
															
														}else
															System.err.println("Operação cancelada");
													}
													
													verificador = false;
												} catch (NumberFormatException a) {
													System.err.println("O Valor digitado está em formato incorreto, tente novamente");
												}
											}while(verificador);
										}else
											System.err.println("Não há disciplinas registradas para poder excluir no momento");
									
									} else if( opcao_interna == 3) { // Listagem Completa
										
										ResultSet resultado = disdao.consulta();
										
										ResultSet confirma = disdao.consulta();
										
										dados = 0;
										
										while(confirma.next()) {
											dados += 1;
										}
										
										if(dados > 0) {
											System.out.println();
											System.out.println("Listando todas as disciplinas // ");
											
											try {
												while (resultado.next()) {
													nome = resultado.getString("nome");
													professorRespons = resultado.getInt("id_professor");
													
													System.out.println("Nome: " + nome + ", Professor Responsável: "+ professorRespons);
												}
											} catch (SQLException e) {
												System.err.println("Não foi possível buscar os dados da disciplina no banco");
											}
										}else
											System.err.println("Não há disciplinas cadastradas no momento para consultar");
									}
								}else
									System.err.println("Não há nenhum professor inserido para cadastrar uma disciplina");
							break;
							
							// CURSO
							
							case 6:
								String nomeCurso;
								
								System.out.println("");
								System.out.println("| Selecione a opção:");
								System.out.println("| 1 - Cadastrar Curso");
								System.out.println("| 2 - Excluir Curso");
								System.out.println("| 3 - Listar Cursos");
								System.out.println("| 0 - Retornar");
								System.out.println("---------");
								System.out.print("| : ");
								
								verificador = true;
								do {
									try {
										opcao_interna = Integer.parseInt(leitor.nextLine());
										verificador = false;
									} catch (NumberFormatException a) {
										System.err.print("Opção inválida, tente novamente");
									}
								}while(verificador);
								
								if(opcao_interna == 1) { // Inserção de curso
									System.out.println("");
									System.out.print("Informe o nome do Curso: ");
									nomeCurso = leitor.nextLine();
									
									Curso curso = new Curso(nomeCurso);
									curso.setCurso(nomeCurso);
									
									curdao = new CursoDAO();
									curdao.insere(curso);
									
									System.out.println("O Curso foi inserido com sucesso!");
									
								}else if(opcao_interna == 2) { // Exclusão
									int id;
									
									ResultSet resultado = curdao.consulta(); // Coletando os dados dos cursos
									
									ResultSet confirma = curdao.consulta();
									
									dados = 0;
									
									while(confirma.next()) {
										dados += 1;
									}
									
									if(dados > 0) {
										
										System.out.println();
										System.out.println("Listando todos os cursos // \n");
										while (resultado.next()) {
											id = resultado.getInt("id_curso");
											nome = resultado.getString("nome");
											System.out.println("ID: "+ id + ", Nome: " + nome);
										}
										
										verificador = true;
										
										try {
											int validador = 0;
											
											do {
												System.out.println();
												System.out.println("Informe o ID do Curso que será removido, ");
												System.out.print("Digite 0 para retornar: ");	
												id = Integer.parseInt(leitor.nextLine());	
												
												validador = curdao.filtra(id);
												if(id == 0) { // Derruba o loop caso a opção seja de cancelar
													validador = 1;
													System.err.println("Operação cancelada, retornando");
												}
												
												if(validador == 0)
													System.err.println("O ID não existe no banco, tente novamente");
												
											}while(validador == 0);
											System.out.println();
										
											if(id != 0) {
												System.out.println("1 - Confirmar Exclusão");
												System.out.println("2 - Cancelar Exclusão");
												System.out.print("| - : ");
												
												if(Integer.parseInt(leitor.nextLine()) == 1) {
													curdao = new CursoDAO();
													curdao.remove(id);	
													
												}else
													System.err.println("Operação cancelada");
											}
										} catch (NumberFormatException a) {
											System.err.println("O valor inserido não está no formato correto");
										}
									}else
										System.err.println("Não há cursos registrados para poder excluir no momento");
									
								}else if(opcao_interna == 3) { // Listagem de cursos
									ResultSet resultado = curdao.consulta();
									
									ResultSet confirma = curdao.consulta();
									
									dados = 0;
									
									while(confirma.next()) {
										dados += 1;
									}
									
									// confirma.beforeFirst(); // Volta o cursor na 1° Linha
									
									if(dados > 0) {
										System.out.println();
										System.out.println("Listando todos os cursos // \n");
										
										try {
											while (resultado.next()) {
												nome = resultado.getString("nome");
												System.out.println(nome);
											}
										} catch (SQLException e) {
											System.err.println("Não foi possível coletar os dados dos cursos do banco de dados");
										}
									}else
										System.err.println("Não há cursos cadastrados no momento para consultar");
								}
							break;
							default:
								if(opcao < -1 || opcao > 6)
									System.err.println("Opção Inválida, tente novamente");
							break;
						}
					}while(opcao_interna != 0); // Só encerra o loop se o valor for 0
				}
			}while(opcao != 0); // Só encerra o loop se o valor for 0
			
			System.out.println("Encerrando//");
		} catch (SQLException e) {
			System.err.println("Não foi possivel fazer a conexão com o banco no momento");
		}
	}
	
	public static void insereGrupo(int registros) throws QtdUsuarios {
		
		String nomeGrupo, tipoGrupo;
		int qtdGrupo;
		boolean confirma_tipo;
		
		GrupoDAO grudao = new GrupoDAO();
		
		Scanner leitor = new Scanner(System.in);
		
		// Inserção de Grupo
		System.out.println("");
		System.out.print("Informe um nome para o grupo: ");
		nomeGrupo = leitor.nextLine();
		
		do {
			System.out.print("Informe a quantidade de integrantes no grupo: ");
			qtdGrupo = Integer.parseInt(leitor.nextLine());
			
			if(qtdGrupo > registros)
				throw new QtdUsuarios("A Quantidade de usuários informados para o grupo é maior que a de usuários inseridos no banco");
			
		}while(qtdGrupo == 0);
		
		confirma_tipo = true;
		
		do {
			System.out.print("Informe o tipo do Grupo: ");
			tipoGrupo = leitor.nextLine().toLowerCase();
			
			// Valida se o tipo de grupo está correto
			if("pesquisa".equals(tipoGrupo) || "trabalho".equals(tipoGrupo))
				confirma_tipo = false;
			else
				System.err.println("Informe o tipo do grupo entre Pesquisa ou Trabalho");
		
		}while(confirma_tipo);
		
		Grupo grupo = new Grupo();
		grupo.setNome(nomeGrupo);
		grupo.setQtdGrupo(qtdGrupo);
		grupo.setTipoGrupo(tipoGrupo);
		
		grudao = new GrupoDAO();
		grudao.insere(grupo);
		
		System.out.println("O Grupo foi criado com sucesso!");
	}
}