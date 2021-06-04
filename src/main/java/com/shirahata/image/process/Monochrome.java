package com.shirahata.image.process;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Monochrome {

	public void image() throws IOException {
		// イメージをBufferedImageへ読みこむ。
		// 以下イメージの操作はBufferedImageを利用して行う。
		BufferedImage readImage = ImageIO.read(new File("C:\\Koala.jpg"));

		// モノクロに変換
		// 元イメージの幅、高さを取得。
		int w = readImage.getWidth();
		int h = readImage.getHeight();

		// 変換結果を書き込むBufferedImageを作成する。
		// サイズは元イメージと同じ幅、高さとする。
		BufferedImage writeImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

		// 1ピクセルづつ処理を行う
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				// ピクセル値を取得
				int c = readImage.getRGB(x, y);
				// 0.299や0.587といった値はモノクロ化の定数値
				int mono = (int) (0.299 * r(c) + 0.587 * g(c) + 0.114 * b(c));
				// モノクロ化したピクセル値をint値に変換
				int rgb = (a(c) << 24) + (mono << 16) + (mono << 8) + mono;
				writeImage.setRGB(x, y, rgb);
			}
		}
		// イメージをファイルに出力する
		ImageIO.write(writeImage, "png", new File("C:\\mono.png"));
	}

	public static int a(int c) {
		return c >>> 24;
	}

	public static int r(int c) {
		return c >> 16 & 0xff;
	}

	public static int g(int c) {
		return c >> 8 & 0xff;
	}

	public static int b(int c) {
		return c & 0xff;
	}

	public static int rgb(int r, int g, int b) {
		return 0xff000000 | r << 16 | g << 8 | b;
	}

	public static int argb(int a, int r, int g, int b) {
		return a << 24 | r << 16 | g << 8 | b;
	}

}
