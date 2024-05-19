package com.example.movies_app.Services;

import android.os.Trace;

import com.example.movies_app.IDO.IDAO;
import com.example.movies_app.Models.Film;

import java.util.ArrayList;
import java.util.List;

public class FilmService implements IDAO<Film> {
    private List<Film> films;
    private static FilmService instance;

    private FilmService() {
        this.films = new ArrayList<>();
    }

    public static FilmService getInstance() {
        if(instance == null)
            instance =  new FilmService();
        return instance;
    }

    @Override
    public Boolean create(Film film) {
       return films.add(film);
    }

    @Override
    public Boolean update(Film film) {
        for (Film film0:films){
            if(film0.getId()==film.getId()){
                 film0=film;
                return true;
            }
        }
        return null;
    }

    @Override
    public Boolean delete(Film film) {
        return films.remove(film);
    }

    @Override
    public List<Film> findall() {
        return films;
    }

    @Override
    public Film findbyid(int id) {
        for (Film film : films){
            if(film.getId()==id){
                return film;
            }
        }
        return null;
    }

    @Override
    public List<Film> findbyname(String name) {
        List<Film> searchfilms= new ArrayList<>();;
        for (Film film : films){
            if(film.getNom().contains(name)){
                 searchfilms.add(film);
            }
        }
        return searchfilms;
    }
}
