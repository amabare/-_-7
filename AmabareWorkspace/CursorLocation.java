/* 
ソースコード作成者: 山本
修正日時: 2024/12/24
ソースコードの動作や役割: マウスカーソルの位置情報を取得する
コメント: 返り値として「return 0;」を設定することができていない
　　　　  あと、
　　　　  　・":"の後にスペースを入れることができていない
　　　　  　・"()"の後に"{}"を書く際はスペースを入れられてない
　　　　  入れることができないのかもしれない
*/

package AmabareWorkspace;

// マウスカーソルの情報を取得するためのクラス
import java.awt.MouseInfo;
// 2次元座標を表すためのクラス
import java.awt.Point;
// トップレベルのウィンドウを表すためのクラス
import javax.swing.JFrame;
// テキストやアイコンを表示するためのクラス
import javax.swing.JLabel;
// 指定された間隔でイベントを発生させるためのクラス
import javax.swing.Timer;

public class CursorLocation extends JFrame {
    private JLabel label;

    // コンストラクタ
    public CursorLocation() {
        label = new JLabel("カーソルの位置: ");
        // labelの文字列をフレームに追加
        add(label);
        // フレームのサイズを設定
        setSize(300, 200);
        // ウィンドウが閉じられたときアプリケーションを終了する
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ウィンドウを表示
        setVisible(true);

        // 100ミリ秒ごとにカーソルの位置を更新
        Timer timer = new Timer(100, _ -> UpdateCursorLocation());
        // タイマーを開始
        timer.start();
    }

    // メソッド
    private void UpdateCursorLocation() {
        // マウスカーソルの現在位置を取得
        Point point = MouseInfo.getPointerInfo().getLocation();
        label.setText("カーソルの位置: X=" + point.x + " Y=" + point.y);
    }
}
