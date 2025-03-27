import axios from 'axios';

const API_URL = '/api/motifs';

export const getAllMotifs = () => axios.get(API_URL);
export const createMotif = (motif) => axios.post(API_URL, motif);
