package util;

import java.math.BigInteger;

/**
 * 
 * @author sekinenaoki
 * 
 */
public class MathTool {

    /**
     * 
     * http://gbb60166.jp/cipher/mathemat.htm
     * 繰り返し二乗法を用いて累乗を高速に行う
     * 
     * 
     * @param base 底となる数
     * @param exponent 累乗する数
     * @param modulus 法にする数
     * @return
     */
    public static BigInteger modPow(BigInteger base, BigInteger exponent, BigInteger modulus){
    	if(modulus.compareTo(BigInteger.ZERO) <= 0){ 
    		throw new IllegalArgumentException("modulus not positive") ; // moddulusが0以下のときエラー,法とする値は0以下ではいけない
    	}
    	
    	BigInteger currentExponent = new BigInteger(exponent+"") ;
        BigInteger rest = new BigInteger("1") ;
        BigInteger currentBase = new BigInteger(base+"") ;
        while(currentExponent.compareTo(BigInteger.ONE)!=0){ 
                //ビットを調べて奇数の判定を行う。BigIntegerのgetLowestSetBitは一番右側にある1ビットの右側の0の個数を返す。00100100←の場合最も右の1の右側のゼロが2つなので2を返す。右側がのゼロが0個の場合奇数である。（例 101 → 5  1001 → 9）
                boolean remainRest = currentExponent.getLowestSetBit()==0 ;
                if(remainRest){
                        rest = rest.multiply(currentBase).mod(modulus) ;
                }
                currentBase = currentBase.multiply(currentBase).mod(modulus) ;
                currentExponent = currentExponent.divide(new BigInteger("2")) ;
        }
        BigInteger result = currentBase.multiply(rest).mod(modulus) ;

    	return result ;
    }

    /**
     * 素数２数を与えて積のオイラーのトーシェント関数の解を求める ((p-1)*(q-1)を求める)
     * @param p 素数
     * @param q 素数
     * @return φ (p*q)
     */
    public static BigInteger phiFunctionByPrimeNumber(BigInteger p, BigInteger q){
    	if(!p.gcd(q).equals(BigInteger.ONE)){ // pとqの最小公倍数が1以外のときtrue
    		throw new ArithmeticException("G.C.D. is not 1") ; // p, qは互いに素でなければならない G.C.M. は Greatest Common Measureの略で意味は最小公倍数、最小公倍数が１のとき互いに素であると言える。
    	}
    	BigInteger p_Sub1 = p.subtract(new BigInteger("1")) ; // p-1
        BigInteger q_Sub1 = q.subtract(new BigInteger("1")) ; // q-1
        BigInteger result = p_Sub1.multiply(q_Sub1); // (p-1) * (q-1) 
    	return result ;
    }
}
