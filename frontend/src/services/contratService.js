import axios from 'axios';

const API_URL = '/api/contrats';

export const getAllContrats = () => axios.get(API_URL);
export const getContratById = (id) => axios.get(`${API_URL}/${id}`);
export const deleteContrat = (id) => axios.delete(`${API_URL}/${id}`);
