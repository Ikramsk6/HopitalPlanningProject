import axios from 'axios';

const API_URL = '/api/personnes';

export const getAllPersonnes = () => axios.get(API_URL);
export const getPersonneById = (id) => axios.get(`${API_URL}/${id}`);
export const deletePersonne = (id) => axios.delete(`${API_URL}/${id}`);
