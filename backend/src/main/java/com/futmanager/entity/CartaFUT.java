package com.futmanager.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartaFUT {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoCarta;
    private Integer ritmo;
    private Integer tiro;
    private Integer pase;
    private Integer regate;
    private Integer defensa;
    private Integer fisico;
    private String imagenUrl;

    @OneToOne
    @JoinColumn(name = "jugador_id")
    private Jugador jugador;
}
