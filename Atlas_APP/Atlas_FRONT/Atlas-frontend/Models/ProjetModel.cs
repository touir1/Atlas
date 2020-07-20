using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Models
{
    public class ProjetModel
    {
        [JsonProperty("id")]
        public long? Id { get; set; }
        [JsonProperty("titre")]
        public String Titre { get; set; }
        [JsonProperty("dateCreation")]
        public DateTime? DateCreation { get; set; }
        [JsonProperty("dateCloture")]
        public DateTime? DateCloture { get; set; }
        [JsonProperty("createdBy")]
        public UserModel createdBy { get; set; }
        [JsonProperty("membres")]
        public List<UserModel> Membres { get; set; }
        [JsonProperty("missions")]
        public List<MissionModel> Missions { get; set; }
        [JsonProperty("rubriques")]
        public List<RubriqueModel> Rubriques { get; set; }

    }
}
