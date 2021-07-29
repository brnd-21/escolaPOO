package media;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import modelo.Conteudo;

public class tipoArquivo extends Conteudo {
		
	private String extensaoArquivo; 
	
	public tipoArquivo(String extensaoArquivo) {
		this.extensaoArquivo = extensaoArquivo;
	}
	
	public void converteTipo(String extensaoArquivo, String nome_arq, Conteudo conteudo) throws IOException {
		System.err.println("Criando o arquivo");
		String texto_conteudo = conteudo.getConteudo();
		
		if(true) {
			// Criando um TXT
			
			FileWriter arq = new FileWriter("c:\\"+ nome_arq +".txt");
			PrintWriter gravarArq = new PrintWriter(arq);
			
			gravarArq.printf("Conteúdo gerado automaticamente pelo Adapter");
			gravarArq.printf("\n--------------------------------------------\n"+ texto_conteudo);
			
			arq.close();
			
			System.err.println("Salvando como um arquivo de texto ( txt )");
		} else if (extensaoArquivo.equalsIgnoreCase("pptx")) {
			
			// Criando um PPTX
			
			FileWriter arq = new FileWriter("c:\\"+ nome_arq +".pptx");
			PrintWriter gravarArq = new PrintWriter(arq);
			
			gravarArq.printf("Conteúdo gerado automaticamente pelo Adapter");
			gravarArq.printf("\n--------------------------------------------\n"+ texto_conteudo);
			
			arq.close();
			
			System.err.println("Salvando como um arquivo do Power Point ( pptx )");
		} else if(extensaoArquivo.equalsIgnoreCase("docx")) {
			
			// Criando um DOCX
			
			FileOutputStream fileOutput = null;
			// XWPFDocument documento = new XWPFDocument;
			
			try {
				
				fileOutput = new FileOutputStream(new File( nome_arq +".docx"));
				
				//XWPFParagraph paragrafo1 = documento.createParagraph();
				//XWPFRun runPaRun1 = paragrafo1.createRun();
				
				//runPaRun1.setText("Conteúdo gerado automaticamente pelo Adapter");
				//runPaRun1.addBreak(); // mesma função do \n ( quebrar linha )
				//runPaRun1.setTexto("--------------------------------------------");
				//runPaRun1.addBreak();
				//runPaRun1.setText(texto_conteudo);
				
				//documento.write(fileOutput);
				
				
			} catch (FileNotFoundException e) {
				
			} catch (IOException e) {
				
			}
			
			FileWriter arq = new FileWriter("c:\\"+ nome_arq +".docx");
			PrintWriter gravarArq = new PrintWriter(arq);
			
			gravarArq.printf("Conteúdo gerado automaticamente pelo Adapter");
			gravarArq.printf("\n--------------------------------------------\n"+ texto_conteudo);
			
			arq.close();
			
			System.err.println("Salvando como um arquivo do Word ( docx )");
		} else if(extensaoArquivo.equalsIgnoreCase("pdf")) {
			
			// Criando um PDF 
			
			//Document document = new Document();
			
			//try {
				//PdfWriter.getInstance(document, new FileOutputStream("c:\\"+ nome_arq +".pdf"));
				//document.open();

				// adicionando um parágrafo no documento
				//document.add(new Paragraph("Conteúdo gerado automaticamente pelo Adapter"));
				//document.add(new Paragraph("\n--------------------------------------------\n"));
				//document.add(new Paragraph(texto_conteudo));
				
			//} catch(DocumentException de) {
				//System.err.println(de.getMessage());
              //} catch(IOException ioe) {
	              //System.err.println(ioe.getMessage());
              //}
	        
			//document.close();
			
			System.err.println("Salvando como um arquivo PDF");
		}
		
		System.err.println("Arquivo criado");
	}	
}