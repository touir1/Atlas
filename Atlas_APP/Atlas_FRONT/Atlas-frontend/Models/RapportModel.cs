using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Models
{
    public class RapportModel
    {
        [JsonProperty("valider")]
        public Boolean Valider { get; set; }
        [JsonProperty("dateCreation")]
        public DateTime DateCreation { get; set; }
        [JsonProperty("dateImputation")]
        public DateTime DateImputation { get; set; }
        [JsonProperty("semaine")]
        public int Semaine { get; set; }
        [JsonProperty("mois")]
        public int Mois { get; set; }
        [JsonProperty("joursSemaine")]
        public int JoursSemaine { get; set; }
        [JsonProperty("duree")]
        public float Duree { get; set; }
        [JsonProperty("user")]
        public UserModel User { get; set; }
        [JsonProperty("rubrique")]
        public RubriqueModel Rubrique { get; set; }

    }
}
