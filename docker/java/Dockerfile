FROM openjdk:8

RUN apt-get update
RUN apt-get install tesseract-ocr ant cmake make sudo zip -y

RUN wget --no-check-certificate https://raw.githubusercontent.com/milq/milq/master/scripts/bash/install-opencv.sh
RUN chmod +x install-opencv.sh
RUN sudo ./install-opencv.sh

RUN export LC_ALL=C
WORKDIR /iamgeOCR