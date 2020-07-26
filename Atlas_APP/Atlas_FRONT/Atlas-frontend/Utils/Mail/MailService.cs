using Atlas_frontend.Models;
using Atlas_frontend.Models.Enums;
using Atlas_frontend.Services;
using Microsoft.AspNetCore.Http;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Mail;
using System.Threading.Tasks;

namespace Atlas_frontend.Utils.Mail
{
    public class MailService : IMailService
    {
        private ICompteService _compteService;
        private IConfigurationService _configurationService;
        private const string _basePath = "./Utils/Mail/Templates";

        public MailService(ICompteService compteService, IConfigurationService configurationService)
        {
            _compteService = compteService;
            _configurationService = configurationService;
        }

        public async Task<bool> SendMailAsync(ISession session, string to, string subject, string fileName, Dictionary<String, String> configuration)
        {
            string fileContent = File.ReadAllText($"{_basePath }/{fileName}");
            foreach (var config in configuration)
            {
                fileContent = fileContent.Replace(config.Key, config.Value);
            }

            return await SendMailAsync(session, to, subject, fileContent, true);
        }

        public async Task<bool> SendMailAsync(ISession session, string to, string subject, string body, bool isHTML)
        {
            try {
                ConfigurationModel from = await _configurationService.GetByKeyAsync(session, ConfigurationKeys.EmailSender);
                ConfigurationModel fromPassword = await _configurationService.GetByKeyAsync(session, ConfigurationKeys.EmailSenderPassword);
                if(from == null || fromPassword == null || from.Value == null || fromPassword.Value == null)
                {
                    return false;
                }

                MailMessage message = new MailMessage();
                SmtpClient smtp = new SmtpClient();
                
                message.From = new MailAddress(from.Value);
                message.To.Add(new MailAddress(to));
                message.Subject = subject;
                message.IsBodyHtml = isHTML;
                message.Body = body;
                
                smtp.Port = 587;
                smtp.Host = "smtp.gmail.com";
                smtp.EnableSsl = true;
                smtp.UseDefaultCredentials = false;
                smtp.Credentials = new NetworkCredential(from.Value, fromPassword.Value);
                smtp.DeliveryMethod = SmtpDeliveryMethod.Network;
                
                smtp.Send(message);
                
                return true;
            }
            catch(Exception e)
            {
                return false;
            }
        }
    }
}
