using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Models
{
    public class CustomModel
    {
        public long Id { get; set; }
        public int LundiDuree { get; set; }
        public int MardiDuree { get; set; }
        public int MercrediDuree { get; set; }
        public int JeudiDuree { get; set; }
        public int VendrediDuree { get; set; }
        public int Annee { get; set; }
        public int Semaine { get; set; }
        public int Mois { get; set; }
        public ProjetModel Projet { get; set; }
        public RubriqueModel Rubrique { get; set; }




    }
}
