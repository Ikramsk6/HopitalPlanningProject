import axios from 'axios';

const API_URL = '/api/roulements';

export const getAllRoulements = () => axios.get(API_URL);
export const getRoulementById = (id) => axios.get(`${API_URL}/${id}`);
export const deleteRoulement = (id) => axios.delete(`${API_URL}/${id}`);
