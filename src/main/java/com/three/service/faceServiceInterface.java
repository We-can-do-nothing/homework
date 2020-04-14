package com.three.service;

public interface faceServiceInterface {

    int faceVerify(String base64);

    boolean faceRegister(String base64, Integer userId);
}
