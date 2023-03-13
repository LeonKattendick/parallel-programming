#include <stdio.h>

#define STB_IMAGE_IMPLEMENTATION

#include "stb_image/stb_image.h"

#define STB_IMAGE_WRITE_IMPLEMENTATION

#include "stb_image/stb_image_write.h"

int main(void) {

    int width = 100, height = 100;
    unsigned char *img = malloc(width * height * 3);

    for (int x = 0; x < width; x++) {
        for (int y = 0; y < height; y++) {

            unsigned char *pixel = img + (x + width * y) * 3;
            pixel[0] = x;
            pixel[1] = y;
            pixel[2] = x;

        }
    }

    stbi_write_jpg("../out/test_written.jpg", width, height, 3, img, 100);
    free(img);

    return 0;
}