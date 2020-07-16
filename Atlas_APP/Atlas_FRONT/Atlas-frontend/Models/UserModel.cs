using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Models
{
    public class UserModel
    {
        public long id { get; set; }
        public String nom { get; set; }
        public String prenom { get; set; }
        public String email { get; set; }
        public String poste { get; set; }
        public String image { get; set; }
        public DateTime dateNaissance { get; set; }
        public DateTime dateContrat { get; set; }
        public List<CompteModel> comptes { get; set; }

    }
}
