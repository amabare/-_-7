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
        Timer timer = new Timer(100, _ -> updateCursorLocation());
        // タイマーを開始
        timer.start();
    }

    private void updateCursorLocation() {
        // マウスカーソルの現在位置を取得
        Point point = MouseInfo.getPointerInfo().getLocation();
        label.setText("カーソルの位置: X=" + point.x + " Y=" + point.y);
    }
}
