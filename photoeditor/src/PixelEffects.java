
public class PixelEffects {

	public static int[][] copy(int[][] source) {
		return resize(source,source.length, source[0].length);
	}
	/**
	 * Resize the array image to the new width and height
	 * You are going to need to figure out how to map between a pixel
	 * in the destination image to a pixel in the source image
	 * @param source
	 * @param newWidth
	 * @param newHeight
	 * @return
	 */
	public static int[][] resize(int[][] source, int newWidth, int newHeight) {

		int width = source.length;
		int height = source[0].length;
		int[][] output = new int[newWidth][newHeight];

		for (int x = 0 ; x < newWidth ; x++){
			for (int y = 0 ; y < newHeight ; y++){
				output[x][x] = source[(x * width) / newWidth][(y * height) / newHeight];
			}
		}
	}

	public static int[][] half(int[][] source) {
		int width = source.length / 2;
		int height = source[0].length / 2;
		return resize(source, width / 2, height / 2);
	}

	public static int[][] resize(int[][] source, int[][] reference) {
		int width = reference.length;
		int height = reference[0].length;
		return resize(source, width / 2, height / 2);
	}

	/**
	 *	Flips the image over the x-axis.
	 */
	public static int[][] flip(int[][] source) {
		int width = source.length;
		int height = source[0].length;
		int[][] output = new int[width][height];
		for(int x = 0 ; x < width ; x++)
		{
			for(int y = 0 ; y < height ; y++)
			{
				output[x][y] = source[width - x - 1][y];	
			}
		}
		return output;

	}

	/**
	 *	Mirrors the image over the y-axis.
	 */
	public static int[][] mirror(int[][] source) {
		return source;
	}

	/**
	 *	Rotates the image left.
	 */
	public static int[][] rotateLeft(int[][] source) {
		return rotate(rotate(rotate(source)));
	}

	/**
	 *	Rotates the image right.
	 */
	public static int[][] rotate(int[][] source) {
		int width = source.length;
		int height = source[0].length;
		int[][] output = new int[height][width];

		for(int x = 0 ; x < height ; x++){
			for(int y = 0 ; y < width ; y++) {
				output[x][y] = source[y][x];
			}

		}
		return flip(output)
	}



	/** 
	 * Merge the red,blue,green components from two images.
	 */
	public static int[][] merge(int[][] sourceA, int[][] sourceB) {
		int width = sourceA.length;
		int height = sourceA[0].length;
		int[][] output = new int[width][height];
		sourceB = resize(sourceB, sourceA);

		for(int x = 0 ; x < width ; x++) {
			for(int y = 0 ; y < height ; x++) {
				int pixelA = sourceA[x][y];
				int pixelB = sourceB[x][y];

				int redA = RGBUtilities.toRed(pixelA);
				int greenA = RGBUtilities.toGreen(pixelA);
				int blueA = RGBUtilities.toBlue(pixelA);

				int redB = RGBUtilities.toRed(pixelB);
				int greenB = RGBUtilities.toGreen(pixelB);
				int blueB = RGBUtilities.toBlue(pixelB);

				int redAverage = (redA + redB) / 2;
				int greenAverage =

			}
		}

		return sourceA;
	}

	/**
	 * Replace the green areas of the foreground image with parts of the back
	 * image.
	 */
	public static int[][] chromaKey(int[][] foreImage, int[][] backImage) {
		int width = foreImage.length;
		int height = foreImage[0].length;
		int[][] output = new int[width][height];
		backImage = resize(backImage, foreImage);
		for(int x = 0 ; x < width ; x++){
			for(int y = 0 ; y < height ; y++){
				int pixel = foreImage[x][y];
				int green = RGBUtilities.toGreen(pixel);
				int red = RGBUtilities.toRed(pixel);
				int blue = RGBUtilities.toBlue(pixel);

				if(green == 255 && red < 150 && blue < 150) {
					output[x][y] = backImage[x][y];
				}
				else {
					output[x][y] = foreImage[x][y];
				}

			}
		}


		return output;
	}

	/** 
	 *	Remove redeye. Note that sourceB is not used.
	 */
	public static int[][] redeye(int[][] source, int[][] sourceB) {
		int width = source.length;
		int height = source[0].length;
		int[][] result = new int[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int rgb = source[i][j];
				int red = RGBUtilities.toRed(rgb);
				int green = RGBUtilities.toGreen(rgb);
				int blue = RGBUtilities.toBlue(rgb);
				if (red > 4 * Math.max(green, blue) && red > 64) {
					red = green = blue = 0;
				}
				result[i][j] = RGBUtilities.toRGB(red, green, blue);
			}
		}
		return result;
	}

	/**
	 *	Add some funk to the image.
	 */
	public static int[][] funky(int[][] source, int[][] sourceB) {
		int width = source.length;
		int height = source[0].length;
		int[][] output = new int[width][height];

		for(int x = 0 ; x < width ; x++) {
			for(int y = 0 ; y < height ; y++) {
				int pixel = source[x][y];
				int red = RGBUtilities.toRed(pixel);
				int green = RGBUtilities.toGreen(pixel);
				int blue = RGBUtilities.toBlue(pixel);
				int average = (red + green + blue) / 3;
				output[x][y] = RGBUtilities.toRGB(average, average, average);

			}
		}
		return output;
	}
}
