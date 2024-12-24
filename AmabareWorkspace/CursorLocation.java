/* 
ソースコード作成者: 山本
修正日時: 2024/12/24
ソースコードの動作や役割: マウスカーソルの位置情報を取得する
コメント: 返り値として int 型の配列(カーソルのx座標, カーソルのy座標)を返す
　　　　  呼び出すたびにカーソルの位置情報を取得する関係上、呼び出し元では無限ループを使用する必要がある
　　　　  無限ループを脱するためには「ボタンを押す行為があればbreak」などとすれば良い
*/

package AmabareWorkspace;

// マウスカーソルの情報を取得するためのクラス
import java.awt.MouseInfo;
// 2次元座標を表すためのクラス
import java.awt.Point;

public class CursorLocation {
    // メソッド
    public int[] UpdateCursorLocation() {
        // マウスカーソルの現在位置を取得
        Point point = MouseInfo.getPointerInfo().getLocation();
        // 返り値としてx座標とy座標を返す
        return new int[] { point.x, point.y };
    }
}
