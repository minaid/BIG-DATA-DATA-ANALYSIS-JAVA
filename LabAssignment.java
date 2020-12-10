import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.Map;
import java.util.Map.Entry;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import org.apache.hadoop.hive.ql.exec.spark.session.SparkSession;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.mllib.feature.HashingTF;
import org.apache.spark.mllib.feature.IDF;
import org.apache.spark.mllib.feature.IDFModel;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.network.protocol.Encoders;

import breeze.util.Encoder;
import scala.Tuple2;
import scala.collection.Iterable;

public class LabAssignment {

		private List<String[]> Array_Terms_Documents = new ArrayList<String[]>();  //every term of each document will be hold in an array
		private List<String> List_all_terms = new ArrayList<String>();            //we need to hold the terms
		private List<double[]> Vector_TFIDF_Documents = new ArrayList<double[]>();   //used for storing document vectors

	public static void main(String[] args) throws FileNotFoundException, IOException{
		
		LabAssignment dataset_similarity = new LabAssignment();
		//sample
		dataset_similarity.readFiles("C:\\Users\\Nick\\eclipse-workspace\\Project\\src\\testagain");  //parse files inside a folder and store in an array
		
		//dataset_similarity.readFiles("C:\\Users\\Nick\\eclipse-workspace\\Project\\src\\_CONTAINS_ALL_DOCUMENTS");
		
		dataset_similarity.tfIdf();    //calculate tfidf
		dataset_similarity.Cosine();		//calculate cosine similarity
	}
	
	public void readFiles(String filePath) throws FileNotFoundException, IOException {
        File[] allfiles = new File(filePath).listFiles();
        BufferedReader in = null;
        for (File f : allfiles) {
                in = new BufferedReader(new FileReader(f));
                StringBuilder sb = new StringBuilder();
                String s = null;
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                }
                String[] tokenizedTerms = sb.toString().replaceAll("[\\W&&[^\\s]]", "").split("\\W+"); 
                for (String term : tokenizedTerms) {
                    if (!List_all_terms.contains(term)) {   //essential because we don't want duplicates
                        List_all_terms.add(term);
                    }
                }
                Array_Terms_Documents.add(tokenizedTerms);
        }

    }
	
	public void tfIdf() {   //A termVector is created related to its own term frequency inverse document frequency 
        double tf; 
        double idf; 
        double tfidf;      //For term frequency inverse document frequency 
        for (String[] docTermsArray : Array_Terms_Documents) {
            double[] tfidfvectors = new double[List_all_terms.size()];
            int count = 0;
            for (String terms : List_all_terms) {
                tf = new Class_TfIdf().tf_calculate(docTermsArray, terms);
                idf = new Class_TfIdf().idf_calculate(Array_Terms_Documents, terms);
                tfidf = tf * idf;
                tfidfvectors[count] = tfidf;
                count++;
            }
            Vector_TFIDF_Documents.add(tfidfvectors);             
        }
    }
	
	public void Cosine() {       //To calculate cosine
        for (int i = 0; i < Vector_TFIDF_Documents.size(); i++) {
            for (int j = 0; j < Vector_TFIDF_Documents.size(); j++) {
                System.out.println("Cosine Similarity between " + i + " & " + j + "  =  "
                                   + new Class_Cosine().cosine_calculate
                                       (
                                         Vector_TFIDF_Documents.get(i), 
                                         Vector_TFIDF_Documents.get(j)
                                       )
                                  );
            }
        }
    }
	
}


