/*
モジュールFormStringToSQLPattern 作成者: 佐々木 修正日時: 12/24
入力が要素数の等しい2つの配列であれば、typearrayに格納された型に応じて整形した要素を返却する配列に格納する
入力にテーブル名が必要だったがメソッドにおいて使用しなかったため用いていない
配列であるかの検査がobjectクラスであるかどうかまでしかできないので不安
*/

function FormStringToSQLPattern(valuearray, typearray){
    
    // 返却する文字列    
    let output = new Array(valuearray.length);
    // valuearrayとtypearrayが配列であれば処理を行う
    // valuearrayとtypearrayの要素数が等しければ処理を行う
    if(valuearray instanceof "Object" && typearray instanceof "Object" && valuearray.length == typearray.length){
        for(let i = 0; i < valuearray.length; i++){
            switch(typearray[i]){
                case "INTEGER":
                    output[i] = valuearray[i];
                break;
                case "CHAR":
                // CHAR、VARCHARの場合は処理が等しいためbreak文を省略
                case "VARCHAR":
                    output[i] = "'" + valuearray[i] + "'";
                break;
                case "NCHAR":
                // NCHARとNVARCHARの場合は処理が等しいためbreak文を省略
                case "NVARCHAR":
                    output[i] = "N'" + valuearray[i] + "'";
                break;
                // DATE型は本システムでは用いていないため省略
                default:
                break;
            }
        }
    }
    return output;
}