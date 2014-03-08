package solution;

import java.math.BigInteger;

import util.MathTool;
/**
 * 
 * @author sekinenaoki
 * 
 * 基本的に「サルにもわかるRSA暗号(http://www.maitou.gr.jp/rsa/rsa01.php)」
 * から得た知識を用いてhttp://ksnctf.sweetduet.info/problem/16を解く
 *
 * 出力された文字列をコピーし、pythonを使って "出力文字列".decode("hex")
 * でフラグを求める 
 */
public class Solution {

		BigInteger publicKey_e = QuestionValues.E ;
		BigInteger publicKey_n = QuestionValues.N ;
		BigInteger encoded_c = QuestionValues.C ;
		BigInteger p = QuestionValues.P ;
		BigInteger q = QuestionValues.Q ;

		public String excute(){
			//秘密鍵を素数p, qとpublicKey_eを用いて求める
	    	BigInteger privateKey_d = new Decode().culcPrivateKey(p, q, publicKey_e) ; 
	    
	    	// 暗号化された文を公開鍵nを法とし秘密鍵dで累乗する。
	    	// この際、秘密鍵の桁数が巨大なので、繰り返し二乗法を利用し高速に累乗を行う。
	    	// 普通に累乗する場合10^1234回累乗するところをこの問題では4096回の累乗に削減出来た。
	    	BigInteger answer = MathTool.modPow(encoded_c, privateKey_d, publicKey_n) ;

	    	return answer.toString() ;
		}
}
