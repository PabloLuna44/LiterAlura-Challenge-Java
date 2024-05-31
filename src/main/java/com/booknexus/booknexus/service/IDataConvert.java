package com.booknexus.booknexus.service;

public interface IDataConvert
{
    <T> T getData(String json,Class<T> Tclass);

}
