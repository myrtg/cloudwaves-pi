export class Categorie {
    idCategorie: number;
    nomCategorie: string;
    sousCategories: SousCategorie[];
  
    constructor(
      idCategorie: number,
      nomCategorie: string,
      sousCategories: SousCategorie[]
    ) {
      this.idCategorie = idCategorie;
      this.nomCategorie = nomCategorie;
      this.sousCategories = sousCategories;
    }
  }
  
  export class SousCategorie {
    idSousCategorie: number;
    nomSousCategorie: string;
    categorie: Categorie;
    projets: Projet[];
    categoryId: number;
  
    constructor(
      idSousCategorie: number,
      nomSousCategorie: string,
      categorie: Categorie,
      projets: Projet[],
      categoryId: number
    ) {
      this.idSousCategorie = idSousCategorie;
      this.nomSousCategorie = nomSousCategorie;
      this.categorie = categorie;
      this.projets = projets;
      this.categoryId = categoryId;
    }
  }
  
  export class Projet {
    idProjet: number;
    titre: string;
    description: string;
    fichierRapport: string;
    sousCategorie: SousCategorie;
  
    constructor(
      idProjet: number,
      titre: string,
      description: string,
      fichierRapport: string,
      sousCategorie: SousCategorie
    ) {
      this.idProjet = idProjet;
      this.titre = titre;
      this.description = description;
      this.fichierRapport = fichierRapport;
      this.sousCategorie = sousCategorie;
    }
  }