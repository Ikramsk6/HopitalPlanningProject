import axios from 'axios';

const API_URL = '/api/besoins';

export const getAllBesoins = () => axios.get(API_URL);
export const createBesoin = (besoin) => axios.post(API_URL, besoin);
