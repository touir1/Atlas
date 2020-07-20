using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Models
{
    public class QuestionModel
    {
        [JsonProperty("id")]
        public long? Id { get; set; }
        [JsonProperty("type")]
        public String Type { get; set; }
        [JsonProperty("libelle")]
        public String Libelle { get; set; }
        [JsonProperty("sujet")]
        public SujetModel Sujet { get; set; }
        [JsonProperty("choix")]
        public ChoixModel Choix { get; set; }
        [JsonProperty("reponses")]
        public List<ResponseModel> Reponses { get; set; }
    }
}
