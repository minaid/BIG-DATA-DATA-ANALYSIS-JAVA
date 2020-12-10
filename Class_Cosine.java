public class Class_Cosine {
    public double cosine_calculate(double[] docVector1, double[] docVector2) {  //2 documents
        double dotProduct = 0.0;
        double magnitude1 = 0.0;
        double magnitude2 = 0.0;
        double cosineSimilarity = 0.0;

        for (int i = 0; i < docVector1.length; i++)   //vectors with same length needed
        {
            dotProduct += docVector1[i] * docVector2[i];    
            magnitude1 += Math.pow(docVector1[i], 2);       //power 2
            magnitude2 += Math.pow(docVector2[i], 2);      //power 2
        }

        magnitude1 = Math.sqrt(magnitude1);              //square root of the above
        magnitude2 = Math.sqrt(magnitude2);             

        if (magnitude1 != 0.0 | magnitude2 != 0.0) {
            cosineSimilarity = dotProduct / (magnitude1 * magnitude2);
        } else {
            return 0.0;
        }
        return cosineSimilarity;
    }
}
