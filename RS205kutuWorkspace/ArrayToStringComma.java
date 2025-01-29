/*
モジュールArrayToStringComma 作成者: 佐々木 修正日時: 1/17
入力配列の各要素を文字列としてカンマで連結して返す
ChangeToCommaPatternメソッド呼び出し元モジュールで入力引数をString型の配列に変換する必要がある
*/

public class ArrayToStringComma{
    public String ChangeToCommaPattern(String[] array){
        String output = null;
        for(int i = 0; i < array.length; i++){
            output += array[i];
            if(i < array.length - 1){
                output += ", ";
            }
        }
        return output;
    }
}