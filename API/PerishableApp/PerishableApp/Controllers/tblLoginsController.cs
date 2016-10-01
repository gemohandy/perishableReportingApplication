using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Description;
using PerishableApp.Models;

namespace PerishableApp.Controllers
{
    public class tblLoginsController : ApiController
    {
        private Perishableapp db = new Perishableapp();

        // GET: api/tblLogins
        public IQueryable<tblLogin> GettblLogins()
        {
            return db.tblLogins;
        }

        // GET: api/tblLogins/5
        [ResponseType(typeof(tblLogin))]
        public IHttpActionResult GettblLogin(int id)
        {
            tblLogin tblLogin = db.tblLogins.Find(id);
            if (tblLogin == null)
            {
                return NotFound();
            }

            return Ok(tblLogin);
        }

        // PUT: api/tblLogins/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PuttblLogin(int id, tblLogin tblLogin)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != tblLogin.Id)
            {
                return BadRequest();
            }

            db.Entry(tblLogin).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!tblLoginExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return StatusCode(HttpStatusCode.NoContent);
        }

        // POST: api/tblLogins
        [ResponseType(typeof(tblLogin))]
        public IHttpActionResult PosttblLogin(tblLogin tblLogin)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.tblLogins.Add(tblLogin);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = tblLogin.Id }, tblLogin);
        }

        // DELETE: api/tblLogins/5
        [ResponseType(typeof(tblLogin))]
        public IHttpActionResult DeletetblLogin(int id)
        {
            tblLogin tblLogin = db.tblLogins.Find(id);
            if (tblLogin == null)
            {
                return NotFound();
            }

            db.tblLogins.Remove(tblLogin);
            db.SaveChanges();

            return Ok(tblLogin);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool tblLoginExists(int id)
        {
            return db.tblLogins.Count(e => e.Id == id) > 0;
        }
    }
}