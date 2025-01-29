/*
モジュールArrayToStringEqual 作成者: 佐々木 修正日時: 1/29
入力が配列ならその配列の各要素を文字列としてイコールで連結して返す
このとき配列の各要素は対応する型に応じて整形する
*/

public class ArrayToStringEqual{
    public String ChangeToEqualPattern(String table, String[] columnArray, String[] valueArray){
        // 返却する文字列
        String output = null;
        // 変形した後のvalueArray
        String[] newValueArray = new String[valueArray.length];
        // 入力配列のサイズが等しければ処理を行う
        if(columnArray.length == valueArray.length){
            // columnArrayの各要素の型を取得
            ShowColumns instanceSC = new ShowColumns();
            String[] columns = instanceSC.showColumns(table, "Type");
            // valueArrayの変形
            FormStringToSQLPattern instanceFSTSP = new FormStringToSQLPattern();
            newValueArray = instanceFSTSP.ChangeToSQLPattern(valueArray, columnType);
            // columnArrayと変形したvalueArrayを=で結合
            for(let i = 0; i < columnArray.length; i++){
                output += (columnArray[i] + " = " + newValueArray[i]);
                // 配列の最後の要素にはカンマをつけないよう処理
                if(i < columnArray.length - 1){
                    output += ", ";
                }
            }
        }
        return output;
    }
}