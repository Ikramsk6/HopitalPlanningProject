import axios from 'axios';

const API_URL = '/api/equipes';

export const getAllEquipes = () => axios.get(API_URL);
export const getEquipeById = (id) => axios.get(`${API_URL}/${id}`);
export const deleteEquipe = (id) => axios.delete(`${API_URL}/${id}`);
