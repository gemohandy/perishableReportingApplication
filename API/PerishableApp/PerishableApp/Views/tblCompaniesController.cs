using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;
using PerishableApp.Models;

namespace PerishableApp.Views
{
    public class tblCompaniesController : Controller
    {
        private Perishableapp db = new Perishableapp();

        // GET: tblCompanies
        public ActionResult Index()
        {
            var tblCompanies = db.tblCompanies.Include(t => t.tblPlace);
            return View(tblCompanies.ToList());
        }

        // GET: tblCompanies/Details/5
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            tblCompany tblCompany = db.tblCompanies.Find(id);
            if (tblCompany == null)
            {
                return HttpNotFound();
            }
            return View(tblCompany);
        }

        // GET: tblCompanies/Create
        public ActionResult Create()
        {
            ViewBag.fk_PlaceID = new SelectList(db.tblPlaces, "Id", "Name");
            return View();
        }

        // POST: tblCompanies/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "Id,fk_PlaceID")] tblCompany tblCompany)
        {
            if (ModelState.IsValid)
            {
                db.tblCompanies.Add(tblCompany);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            ViewBag.fk_PlaceID = new SelectList(db.tblPlaces, "Id", "Name", tblCompany.fk_PlaceID);
            return View(tblCompany);
        }

        // GET: tblCompanies/Edit/5
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            tblCompany tblCompany = db.tblCompanies.Find(id);
            if (tblCompany == null)
            {
                return HttpNotFound();
            }
            ViewBag.fk_PlaceID = new SelectList(db.tblPlaces, "Id", "Name", tblCompany.fk_PlaceID);
            return View(tblCompany);
        }

        // POST: tblCompanies/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "Id,fk_PlaceID")] tblCompany tblCompany)
        {
            if (ModelState.IsValid)
            {
                db.Entry(tblCompany).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            ViewBag.fk_PlaceID = new SelectList(db.tblPlaces, "Id", "Name", tblCompany.fk_PlaceID);
            return View(tblCompany);
        }

        // GET: tblCompanies/Delete/5
        public ActionResult Delete(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            tblCompany tblCompany = db.tblCompanies.Find(id);
            if (tblCompany == null)
            {
                return HttpNotFound();
            }
            return View(tblCompany);
        }

        // POST: tblCompanies/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            tblCompany tblCompany = db.tblCompanies.Find(id);
            db.tblCompanies.Remove(tblCompany);
            db.SaveChanges();
            return RedirectToAction("Index");
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }
    }
}
