/*
モジュールArrayToStringComma 作成者: 佐々木 修正日時: 12/24
入力が配列ならその配列の各要素を文字列としてカンマで連結して返す
配列であるかの検査がojjectクラスであるかどうかまでしかできないので不安
*/

function ArrayToStringComma(array){
    // 返却する文字列
    let output = null; 
    // 配列であれば処理を行う
    if(array instanceof "object"){
        for(let i = 0; i < array.length; i++){
            output += array[i];
            // 配列の最後の要素にはカンマをつけないよう処理
            if(i < array.length - 1){
                output += ", ";
            }
        }
    }
    return output;
}