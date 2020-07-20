using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Models
{
    public class ChoixModel
    {
        [JsonProperty("id")]
        public long? Id { get; set; }
        [JsonProperty("ordre")]
        public String Ordre { get; set; }
        [JsonProperty("libelle")]
        public String Libelle { get; set; }
        [JsonProperty("question")]
        public QuestionModel Question { get; set; }
    }
}
