package org.alsea.services;

import org.alsea.models.country;
import org.alsea.models.user;
import org.alsea.models.userUpdate;
import org.alsea.repos.users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class userService {

    @Autowired
    private users usersRepo;
   @Autowired
    private countryService countryService;

    public List<user> getAllUsers(){
        return usersRepo.findAll();
    }

    public Map createUser(user usuario) {

        Map map = new HashMap();


        boolean existEmail = usersRepo.existsByMail(usuario.getMail());
        if(!existEmail)
        {
            //aqui convertimos fecha UTC a UTC con zona horaria
            List<country> countrys = countryService.getAllCountrys();
            List<country> filtercountrys = filterByCode(countrys, usuario.getCodigo());
            if(filtercountrys.size()==1)
            {
                String TimeZone = filtercountrys.get(0).getUtc();
                ZonedDateTime fechaZonedUtc = usuario.getFecha_registro().atZone(ZoneId.of("UTC"));

                // Aplicar la nueva zona horaria (UTC-04)
                ZoneId zonaHoraria = ZoneId.of(TimeZone);
                ZonedDateTime fechaEnNuevaZona = fechaZonedUtc.withZoneSameInstant(zonaHoraria);

                // Formatear la fecha en la nueva zona horaria
                String fechaEnNuevaZonaStr = fechaEnNuevaZona.format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
                LocalDateTime resultadolocal=fechaEnNuevaZona.toLocalDateTime();
                usuario.setFecha_registro(resultadolocal);
                usuario.setFecha_actualizacion(null);
                usersRepo.save(usuario);
                map.put("message","usuario agregado con exito");
                map.put("code",200);

            }

            else{
                map.put("message","codigo de pais, invalido");
                map.put("code",404);
            }



        }
        else
        {
            map.put("message","Ya existe un usuario con el correo previo");
            map.put("code",500);
        }

        return map;



    }

    public static List<country> filterByCode(List<country> paises, String codigo) {
        return paises.stream()
                .filter(pais -> pais.getCodigo().equalsIgnoreCase(codigo))
                .collect(Collectors.toList());
    }

    public Optional<user> getUserById(Long id) {
        return usersRepo.findById(id);
    }

    public user updateUser(Long id, userUpdate usuario) {
        Optional<user> optionalUsuario = usersRepo.findById(id);
        if (optionalUsuario.isPresent()) {
            user curruser= optionalUsuario.get();
            curruser.setNombre(usuario.getNombre());
            curruser.setMail(usuario.getMail());
            curruser.setTelefono(Long.valueOf(usuario.getTelefono()));
            List<country> countrys = countryService.getAllCountrys();
            List<country> filtercountrys = filterByCode(countrys, curruser.getCodigo());
            String TimeZone = filtercountrys.get(0).getUtc();
            ZonedDateTime fechaZonedUtc = usuario.getFecha_actualizacion().atZone(ZoneId.of("UTC"));
            ZoneId zonaHoraria = ZoneId.of(TimeZone);
            ZonedDateTime fechaEnNuevaZona = fechaZonedUtc.withZoneSameInstant(zonaHoraria);

            // Formatear la fecha en la nueva zona horaria
            String fechaEnNuevaZonaStr = fechaEnNuevaZona.format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
            LocalDateTime resultadolocal=fechaEnNuevaZona.toLocalDateTime();
            curruser.setFecha_actualizacion(resultadolocal);
            return usersRepo.save(curruser);

        }
return null;
    }

    public void deleteUser(Long id) {
        usersRepo.deleteById(id);
    }




}
