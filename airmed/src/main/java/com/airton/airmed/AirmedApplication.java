package com.airton.airmed;

import com.airton.airmed.model.Medico;
import com.airton.airmed.model.Paciente;
import com.airton.airmed.repository.MedicoRepository;
import com.airton.airmed.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class AirmedApplication{


	public static void main(String[] args)  {
		SpringApplication.run(AirmedApplication.class, args);
	}

    @Bean
    @Profile("!test")
    public CommandLineRunner carregarDadosIniciais(
            MedicoRepository medicoRepository,
            PacienteRepository pacienteRepository
    ) {
        return args -> {
            inserirMedicos(medicoRepository);
            inserirPacientes(pacienteRepository);
        };
    }


    private void inserirMedicos( MedicoRepository medicoRepository) {

        if (medicoRepository.count() == 0) {

            Medico cardiologista = new Medico();
            cardiologista.setNome("Dr. Drauzio Varela");
            cardiologista.setEspecialidade("Cardiologista");

            Medico ortopedista = new Medico();
            ortopedista.setNome("Dr. Paulo Muzy");
            ortopedista.setEspecialidade("Ortopedista");

            medicoRepository.save(cardiologista);
            medicoRepository.save(ortopedista);
        }
    }

    private void inserirPacientes( PacienteRepository pacienteRepository) {

        if (pacienteRepository.count() == 0) {

            Paciente airton = new Paciente();
            airton.setNome("Airton Silva");
            airton.setCpf("11111111111");

            Paciente maria = new Paciente();
            maria.setNome("Maria Oliveira");
            maria.setCpf("22222222222");

            pacienteRepository.save(airton);
            pacienteRepository.save(maria);
        }
    }



}
