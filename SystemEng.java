
package systemeng;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexableFieldType;
import org.apache.lucene.index.Terms;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 *
 * @author farazpc.ir
 */
public class SystemEng {
   
    public static String TextDir = "C:\\Users\\farazpc.ir\\Downloads\\Compressed\\tccorpus";
    public static String IndexDir = "C:\\Users\\farazpc.ir\\Documents\\NetBeansProjects\\SystemEng\\indexfile";
    public static Analyzer analyzer = new EnglishAnalyzer();
    
    public  static void indexing(String indexdir) throws IOException, ParseException{
        Path path = Paths.get(indexdir);
        Directory directory = FSDirectory.open(path);
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter iwriter = new IndexWriter(directory, config);
        File texts = new File(TextDir);
        File [] sorted = texts.listFiles();
        FieldType fl = new FieldType();
        fl.setStoreTermVectors(true);
        fl.setTokenized(true);
        fl.setIndexOptions(IndexOptions.DOCS_AND_FREQS);
         for (int i=0; i<sorted.length ; i++) {
            File file = sorted[i];
            Document doc = new Document();
            doc.add(new Field("content", new FileReader(file), fl));
            doc.add(new StringField("filename", file.getName(), Field.Store.YES));
            iwriter.addDocument(doc);
        }
          iwriter.close();
          String input = "a";
         
          System.out.println("welcome to Masoud search engine insert quary id");
         
          Scanner sca = new Scanner(System.in);
          int qid = sca.nextInt();
          System.out.println("Now insert the quary you want");
          input= sca.next();
          QueryParser qp = new QueryParser("content",
          new EnglishAnalyzer());
        
          Directory directory2 = FSDirectory.open(path);
          IndexReader indexReader = DirectoryReader.open(directory2);
          IndexSearcher isearcher = new IndexSearcher(indexReader);
          isearcher.setSimilarity(new mysimilatri());
          Query query;
          query = qp.parse(input);
          TopDocs tp = isearcher.search(query,10);
          
          FileWriter fw = new FileWriter("results.txt",true);
          BufferedWriter bw = new BufferedWriter(fw);
          ScoreDoc[] topscore = tp.scoreDocs;
          for( int i=0; i<topscore.length;i++){
              ScoreDoc now = topscore[i];
              int id = now.doc;
               float score =now.score;
                bw.write(qid+" "+ "Q0"+"  "+ id+"  "+ score);
                bw.newLine();
                 
          }
          
         bw.close();
          

    }
    public static void main(String[] args) throws IOException, ParseException {
       indexing(IndexDir);   
    }
    
}
