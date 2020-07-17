using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Models
{
    public class MissionModel
    {
        [JsonProperty("id")]
        public long Id { get; set; }
        [JsonProperty("type")]
        public String Type { get; set; }
        [JsonProperty("date")]
        public DateTime Date { get; set; }
        [JsonProperty("duree")]
        public float Duree { get; set; }
        [JsonProperty("lieu")]
        public String Lieu { get; set; }
        [JsonProperty("frais")]
        public List<FraisModel> Frais { get; set; }
        [JsonProperty("facturations")]
        public List<FacturationModel> Facturations { get; set; }
        [JsonProperty("projet")]
        public ProjetModel Projet { get; set; }

    }
}
