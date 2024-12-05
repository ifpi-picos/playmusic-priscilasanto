// import java.util.Scanner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.util.List;
import java.util.ArrayList;

import dominio.Album;
import dominio.Artista;
import dominio.Musica;

public class App {
    public static void main(String[] args) {
        Musica musica1 = new Musica("Dandelions",  "Música pop", "./assets/musicas_internacionais/dandelions.wav", 194);

        Musica musica2 = new Musica("Photograph", "pop", "./assets/musicas_internacionais/Photograph.wav", 188);

        Musica musica3 = new Musica("too good at goodbyes", "pop", "./assets/musicas_internacionais/too-good-at-goodbyes.wav", 185);

        List<Musica> musicas1 = new ArrayList<>();
        musicas1.add(musica1);
        musicas1.add(musica2);
        musicas1.add(musica3);

        Album album1 = new Album("Romanticas", 2024, musicas1, "./assets/capas/X_cover.png");

        Artista musica = new Artista("Ed Sheeran ");
        musica.addAlbum(album1);

        AudioPlayer player = new AudioPlayer();

        JButton playStopButton = new JButton("Play");

        player.loadAudios(musica.getAlbuns().get(0).getMusicas());
        
        playStopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (!player.getIsPlaying()) {
                    player.playAudio();
                    playStopButton.setText("parar");
                } else {
                    player.stopAudio();
                    playStopButton.setText("Play");
                }
            }
        });

        JButton nextButton = new JButton("Próximo");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                player.nextAudio();
                playStopButton.setText("Parar");
            }
        });

        JButton previousButton = new JButton("voltar");
        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                player.previousAudio();
                playStopButton.setText("Parar");
            }
        });

        JOptionPane.showOptionDialog(
                null,
                "Você está ouvindo romanticas",
                "PlayMusic",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                musica.getAlbuns().get(0).getCapa(),
                new Object[] { previousButton, playStopButton, nextButton },
                playStopButton);

        if (player.getAudioClip() != null) {
            player.getAudioClip().close();
        }
    }
}
