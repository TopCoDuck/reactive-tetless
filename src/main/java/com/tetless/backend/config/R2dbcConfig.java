package com.tetless.backend.config;

import com.tetless.backend.repository.disk.EscrowRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

//@EnableR2dbcRepositories(basePackageClasses = EscrowRepository.class)
//@EnableR2dbcAuditing
@Configuration
public class R2dbcConfig {
}
