package com.maen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1")
public class CustomerController {

    //Inyectar el objecto definido para poder acceder a la sesion.
    @Autowired
    private SessionRegistry sessionRegistry;

    @GetMapping("/index")
    public String index(){
        return "Hello World!";
    }

    @GetMapping("/index2")
    public String index2(){
        return "Hello World Not SECURED!";
    }

    @GetMapping("/session")
    public ResponseEntity<?> getDetailsSession(){

        //Datos a obtener de usuario de inicio sesion.
        String sessionId = "";
        User userObject = null;

        //Recuperar los datos de la sesion del usuario.
        List<Object> sessions = sessionRegistry.getAllPrincipals();

        //Recorrer la lista (sessions).
        for (Object session : sessions) {
            if (session instanceof User){
                userObject = (User) session;
            }

            /**
             * Recuperar el Id de la sesion.
             * Devuelve la informacion de todas las sesiones.
             */
            List<SessionInformation> sessionInformations = sessionRegistry.getAllSessions(session, false);

            //Se recorre tambien el sessionInformation.
            for (SessionInformation sesionInformation : sessionInformations) {
                sessionId = sesionInformation.getSessionId(); //Recuperar el Id de la sesion.
            }
        }

        /**
         * La informacion obtenida se retornara como un json.
         * Con esto se retorna la informacion referente a la sesion y al inicio de sesion del usuario.
         */

        Map<String, Object> response = new HashMap<>();
        response.put("Response: ", "Hello World");
        response.put("SessionId: ", sessionId);
        response.put("SessionUser: ", userObject);

        return ResponseEntity.ok(response);
    }
}
