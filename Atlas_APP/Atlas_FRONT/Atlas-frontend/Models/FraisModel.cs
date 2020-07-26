using Microsoft.AspNetCore.Http;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Models
{
    public class FraisModel
    {
        [JsonProperty("id")]
        public long? Id { get; set; }
        [JsonProperty("type")]
        public String Type { get; set; }
        [JsonProperty("status")]
        public String Status { get; set; }
        [JsonProperty("description")]
        public String Description { get; set; }
        [JsonProperty("justificatif")]
        public String Justificatif { get; set; }
        [JsonProperty("remboursable")]
        public Boolean Remboursable { get; set; }
        [JsonProperty("user")]
        
        public UserModel User { get; set; }
        [JsonProperty("mission")]
        public MissionModel Mission { get; set; }

        [JsonProperty("frais")]
        public float Frais { get; set; }


        [JsonIgnore]
        [Display(Name ="Justificatif")]
        public IFormFile Justifiatiffile { get; set; }
        
    }

}
