export interface Event{
    id: number;
    nom: string;
    titre: string;
    description: string;
    dateDebut: Date;
    dateFin: Date;
    nb_places: number;
    tutor: string;
    image: File;
}