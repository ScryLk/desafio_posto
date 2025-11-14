package br.com.desafioposto.config;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("api") 
public class JaxRsConfig extends Application {
    // vazia apenas para indicar o caminho base
}
