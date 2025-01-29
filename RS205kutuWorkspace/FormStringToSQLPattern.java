/*
モジュールFormStringToSQLPattern 作成者: 佐々木 修正日時: 1/17
入力が要素数の等しい2つの配列であれば、typearrayに格納された型に応じて整形した要素を返却する配列に格納する
入力にテーブル名が必要だったがメソッドにおいて使用しなかったため用いていない

DB呼び出しモジュールを用いる人に向けて: 
入力引数typeArrayの要素としては、[INTEGER, CHAR, VARCHAR, NCHAR, NVARCHAR]のいずれかを用いてください...
*/

public class FormStringToSQLPattern(){
    public String ChangeToSQLPattern(String[] valueArray, String[] typeArray){
    // 返却する文字列    
    String[] output = new String[valuearray.length];
    // valueArrayとtypeArrayの要素数が等しければ処理を行う
    if(valueArray.length == typeArray.length){
        for(let i = 0; i < valueArray.length; i++){
            switch(typeArray[i]){
                case "int":
                    output[i] = valueArray[i];
                break;
                case "char":
                // CHAR、VARCHARの場合は処理が等しいためbreak文を省略
                case "varchar":
                    output[i] = "'" + valueArray[i] + "'";
                break;
                case "nchar":
                // NCHARとNVARCHARの場合は処理が等しいためbreak文を省略
                case "nvarchar":
                    output[i] = "N'" + valueArray[i] + "'";
                break;
                // DATE型は本システムでは用いていないため省略
                default:
                break;
            }
        }
    }
    return output;
    }
}