import axios from 'axios';

const API_URL = '/api/jours';

export const getAllJours = () => axios.get(API_URL);
export const getJourById = (id) => axios.get(`${API_URL}/${id}`);
export const deleteJour = (id) => axios.delete(`${API_URL}/${id}`);
    