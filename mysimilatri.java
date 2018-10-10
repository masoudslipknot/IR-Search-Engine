/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systemeng;

import java.io.IOException;
import static java.lang.Math.log;
import java.lang.reflect.Method;
import static jdk.nashorn.internal.objects.NativeMath.max;
import org.apache.lucene.index.FieldInvertState;
import org.apache.lucene.index.LeafReaderContext;
import org.apache.lucene.search.CollectionStatistics;
import org.apache.lucene.search.TermStatistics;
import org.apache.lucene.search.similarities.Similarity;
import org.apache.lucene.search.similarities.TFIDFSimilarity;
import org.apache.lucene.util.BytesRef;

/**
 *
 * @author farazpc.ir
 */
public class mysimilatri extends TFIDFSimilarity{

    @Override
    public float tf(float f) {
      // float result = (float) (0.5 + (0.5*f)/max(f));
      // return result;
      float result = (float) Math.sqrt(f);
      return result;
    }

    @Override
    public float idf(long l, long l1) {
       // float logo = (float) log((l1 - l)/l1);
      //  float result= (float) max(0, logo);
      float result= (float)log(1 + (l1 - l + 0.5)/(l+ 0.5));
        //float result = (float) (1 + log((l1+1)/(l+1)));
        return result;
        
    }

   
    @Override
    public float sloppyFreq(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float scorePayload(int i, int i1, int i2, BytesRef br) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float lengthNorm(int i) {
        return (float) (1/Math.sqrt(i));
    }

   

   
    
}
