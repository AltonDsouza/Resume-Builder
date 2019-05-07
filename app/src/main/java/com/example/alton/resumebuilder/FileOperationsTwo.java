package com.example.alton.resumebuilder;


import android.util.Log;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.itextpdf.text.pdf.draw.LineSeparator;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;


public class FileOperationsTwo {
    public static final String RESULT="";
    public FileOperationsTwo() {
    }

    public Boolean write(String fname, String fcontent, String fcontentphone,
                         String fcontentemail,String fcontentaddress,String fcontentobjective,
                         String fcontenteducation,String fcontentcourse,String fcontenttechskill,
                         String fcontentexperience, String fcontentproject
    ) {
        try {
            String fpath = "/sdcard/" + fname + ".pdf";
            File file = new File(fpath);
            // If file does not exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            // step 1
            Document document = new Document();
            // step 2
            PdfWriter writer=PdfWriter.getInstance(document,new FileOutputStream(file.getAbsoluteFile()));
            PrintWriter pwriter;
            pwriter=new PrintWriter(System.out,true);
            // step 3
            document.open();
            // step 4
            Font f;
            f = FontFactory.getFont(FontFactory.COURIER_OBLIQUE, 14, Font.BOLD);
            PdfContentByte cb=writer.getDirectContent();

            Paragraph pname=new Paragraph(fcontent,f);
            float indent=10;
            pname.setIndentationLeft(indent);
            document.add(pname);

            Paragraph pphone=new Paragraph(fcontentphone,f);
            indent=10;
            pphone.setIndentationLeft(indent);
            document.add(pphone);

            Paragraph pmail=new Paragraph(fcontentemail,f);
            indent=10;
            pmail.setIndentationLeft(indent);
            document.add(pmail);

            Paragraph paddress=new Paragraph(fcontentaddress,f);
            indent=10;
            paddress.setIndentationLeft(indent);
            document.add(paddress);


            document.add(Chunk.NEWLINE);


            Font f1;
            f1=FontFactory.getFont("Helvetica", 18,Font.BOLDITALIC);
            Paragraph co=new Paragraph("CAREER OBJECTIVE",f1);
            document.add(co);
            document.add(new Paragraph(fcontentobjective));

            document.add( Chunk.NEWLINE );
            document.add(new LineSeparator());
            document.add( Chunk.NEWLINE );

            Paragraph edu=new Paragraph("EDUCATION",f1);
            document.add(edu);
            document.add(new Paragraph(fcontenteducation));

            document.add( Chunk.NEWLINE );
            document.add(new DottedLineSeparator());
            document.add( Chunk.NEWLINE );

            Paragraph courses=new Paragraph("COURSES UNDERTAKEN",f1);
            document.add(courses);
            document.add(new Paragraph(fcontentcourse));

            document.add( Chunk.NEWLINE );
            document.add(new LineSeparator());
            document.add( Chunk.NEWLINE );

            Paragraph skills=new Paragraph("SKILLS AND TECHNOLOGIES",f1);
            document.add(skills);
            document.add(new Paragraph(fcontenttechskill));

            document.add( Chunk.NEWLINE );
            document.add(new DottedLineSeparator());
            document.add( Chunk.NEWLINE );

            Paragraph tra=new Paragraph("TRAINING OR INTERNSHIPS",f1);
            document.add(tra);
            document.add(new Paragraph(fcontentexperience));

            document.add( Chunk.NEWLINE );
            document.add(new LineSeparator());
            document.add( Chunk.NEWLINE );



            Paragraph projects=new Paragraph("Projects",f1);
            document.add(projects);
            document.add(new Paragraph(fcontentproject));
            //document.add(new LineSeparator());

            // step 5
            document.close();

            Log.d("Success", "Success");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (DocumentException e) {
            e.printStackTrace();
            return false;
        }
    }


}
