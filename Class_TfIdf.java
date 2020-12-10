import java.util.List;

public class Class_TfIdf {
	//calculate the term frequency of termToCheck
    public double tf_calculate(String[] totalterms, String termToCheck) {  //totalterms -> Array of all words in document,termToCheck -> term
        double count = 0;             //count total of termToCheck
        for (String s : totalterms) {
            if (s.equalsIgnoreCase(termToCheck)) {
                count++;
            }
        }
        return count / totalterms.length;    //term frequency of termToCheck
    }
    //calculate the inverse document frequency of termToCheck
    public double idf_calculate(List<String[]> allTerms, String termToCheck) {  //allTerms -> all terms of all documents
        double count = 0;
        for (String[] ss : allTerms) {
            for (String s : ss) {
                if (s.equalsIgnoreCase(termToCheck)) {
                    count++;
                    break;
                }
            }
        }
        return Math.log(allTerms.size() / count);   //inverse document frequency
    }
}

