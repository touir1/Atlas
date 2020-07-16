using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Models
{
    public class UserModel
    {
        public long Id { get; set; }
        public String Nom { get; set; }
        public String Prenom { get; set; }
        public String Email { get; set; }
        public String Poste { get; set; }
        public String Image { get; set; }
        public DateTime DateNaissance { get; set; }
        public DateTime DateContrat { get; set; }
        public List<CompteModel> Comptes { get; set; }

    }
}
