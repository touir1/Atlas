using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Models
{
    public class ReclamationModel
    {
        [JsonProperty("id")]
        public long? Id { get; set; }
        [JsonProperty("libelle")]
        public String Libelle { get; set; }
        [JsonProperty("type")]
        public String Type { get; set; }
        [JsonProperty("niveau")]
        public String Niveau { get; set; }
        [JsonProperty("status")]
        public String Status { get; set; }
        [JsonProperty("user")]
        public UserModel User { get; set; }
    }
}
