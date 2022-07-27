package co.edu.unicauca.APIHappLab.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import co.edu.unicauca.APIHappLab.model.persona;
import co.edu.unicauca.APIHappLab.service.persona_service;

@Component
public class InfoAdditionalToken implements TokenEnhancer {
    @Autowired
	private persona_service usuarioService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		persona usuario = usuarioService.findPersonaByEmail(authentication.getName());
		Map<String, Object> info = new HashMap<>();
		info.put("info_adicional", "Hola que tal!: ".concat(authentication.getName()));
		
		info.put("nombre", usuario.getNombres());
		info.put("apellido", usuario.getApellidos());
		info.put("email", usuario.getEmail());
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		
		return accessToken;
	}
}
