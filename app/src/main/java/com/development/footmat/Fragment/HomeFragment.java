package com.development.footmat.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.development.footmat.adapters.AdapterCategory;
import com.development.footmat.databinding.FragmentHomeBinding;
import com.development.footmat.models.CategoryModel;
import com.development.footmat.component.MyDialog;
import com.development.footmat.viewModel.HomeViewModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class HomeFragment extends Fragment {

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private AdapterCategory adapterCategory;
    FirebaseFirestore db ;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        db = FirebaseFirestore.getInstance();


        adapterCategory = new AdapterCategory(getContext());
        MyDialog.showDialog(getActivity());
        binding.recyclerView.setAdapter(adapterCategory);

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        binding.recyclerView.setLayoutManager(mLinearLayoutManager);

        homeViewModel.getCategoryMutableLiveData().observe(getActivity(), userListUpdateObserver);



        addItem();

        return root;
    }

    private void addItem() {
//
        String baseImageUrl = "https://firebasestorage.googleapis.com/v0/b/food-mat.appspot.com/o/image%2F";
//
//
        Map<String, Object> user = new HashMap<>();
//        user.put("description","خواص شیر متعدد است و از راه\u200Cهای مختلفی می\u200Cتواند سلامت جسم شما را تضمین کند. این ماده غذایی دارای مواد مغذی مهمی مانند کلسیم، فسفر، ویتامین\u200Cهای گروه B، پتاسیم و ویتامین D است که یک منبع عالی برای تامین پروتئین به\u200Cحساب می\u200Cآید.\n" +
//                "\n" +
//                "بسیاری از افراد قادر به هضم لاکتوز موجود در شیر نیستند یا به دلایل شخصی از مصرف آن خودداری می\u200Cکنند. اما برای کسانی که می\u200Cتوانند بدون هیچ مشکلی شیر بخورند، خواص این ماده غذایی می\u200Cتواند سلامت بدنی\u200Cشان را تضمین کند.\n" +
//                "\n" +
//                "برای بهره\u200Cمندی از خواص شیر و تامین سلامت جسم خود، بهترین زمان مصرف شیر در رژیم غذایی خود را پیدا و به\u200Cصورت منظم مصرف کنید.");
//        user.put("name", "شیر");
//        user.put("image", baseImageUrl+"milk.jpg");
//
//        db.collection("dairy_item")
//                .add(user)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Log.d("Adding to Firebase", "DocumentSnapshot added with ID: " + documentReference.getId());
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w("Adding to Firebase", "Error adding document", e);
//                    }
//                });


        user.put("description", "عدس یكی از حبوبات است كه از قدیم الایام در اكثر نقاط دنیا كشت می شده است . گیاه شناسان اعتقاد دارند كه عدس حتی قبل از تاریخ نیز وجود داشته است . عدس از نظر مقدر پروتئین بسیار غنی است و دربین گیاهان فقط لوبیای سویا بیشتر از عدس پروتئین دارد . عدس دو نوع است:عدس سبز که ریز و درشت دارد وعدس قرمز که آن را لپه مى کنند و به نام «دال» معروف است.عدس به حالت وحشى و خودرو در مناطق آسیاى غربى، یونان، ایتالیا و در تمام مناطق مدیترانه اى کاشته مى شود. در ایران در بیشتر مناطق عمل مى آید و تکثیر آن معمولاً در اوایل بهار صورت مى گیرد. بهترین نوع عدس آن است که سفید، پهن و زودپز باشد و اگر در آب خیس نمایند، سیاه نشود. عدس از نظر طب قدیم ایران معتدل و خشک است و مانند دیگر گیاهان پوست و دانه آن دارای اثرات متضاد یکدیگر می\u200Cباشد. یعنی پوست عدس گرم و خود عدس قابض است. به عبارت دیگر پوست آن ملین و مغز آن ضد اسهال می\u200Cباشد. عدس یک غذای کامل است و انرژی لازم را برای کارهای بدنی برای انسان تامین می\u200Cکند. عدس زیاد کننده ترشحات شیر است و مادران شیرده حتما باید از آن استفاده کنند. عدس را روی پوست ملتهب بگذارید التهاب را برطرف می\u200Cکند. برای درمان زخمهای دهان و گلو ودیفتری عدس را پخته و آب آنرا غرغره کنید. برای تقویت معده و برطرف کردن گاز معده عدس پخته را با سرکه میل کنید. آش عدس با روغن بادام برای دوران نقاهت بهترین غذاست. پماد در عدس با سرکه برای ترک دست و پا که به علت سرما عارض شده سودمند است. عدس را می\u200Cتوان به عنوان مسهل هم مصرف کرد چون دارای لعاب زیادی است که در درمان ناراحتیهای روده نیز موثر است. عدس پخته را به عنوان مرهم برای زخم نیز می\u200Cتوان بکار برد. دانه عدس داراى ویتامین هاى «آ»، «ب»، «ث»، «پ پ»، «6B»، «2B»، «1B» و فلزاتى چون آهن و کلسیم و از همه بیشتر فسفر مى باشد و از آلبومین، هیدرات دوکربن، چربى، آب، سلولز، پتاسیم، منیزیم، ترکیب پروتئین، تریپتونان، ترهئونین، ایزولوسین، لوسین، لیزین، میتونین، سیستین، فنیل آلانین، نیروزین و والین برخوردار است. عدس از آن دسته دانه هاى خوراکى و مغذى است که خیلى زود مى پزد. لعابدار است و در موارد ناراحتى هاى روده مفید مى باشد.عدس پخته را در هر مرهمى براى پاک کردن زخم هاى بدن به کار مى برند. مسواک زدن با سوخته گردعدس براى سفید کردن دندان مفید است. عدس نفّاخ، دیرهضم و کاهنده میل جنسى است. زیاده روى در مصرفش، غلیظ کننده خون است و جریان خون را در رگ هاى باریک کُند مى کند و ممکن است موجب کم شدن قوه بینایى، سوزش ادرار، قولنج، ناراحتى هاى عصبى، بند آمدن قاعدگى و درد و شدت بواسیر شود. به همین جهت معمولاً عدس را با روغن کنجد یا روغن بادام و سرکه و سایر مخلفات دیگر طبخ کرده و مى خورند. بهترین طریقه خوردن عدس ، جوانه آن است که ضمن اینکه معایب فوق را ندارد، محسّنات خوب آن را به مقدار فوق العاده بیشترى نیز به همراه دارد. ارزش غذایى عدس در هر وعده (یک دوم لیوان) کالرى 80 کل چربى کمتر از 1 درصد چربى اشباع صفر درصد کلسترول صفر درصد سدیم صفر درصد کل کربوهیدرات 22 (7 درصد) گرم الیاف گیاهى خوراکى 11 (44 درصد) گرم قند صفر درصد پروتئین 8 گرم ویتامین «آ» صفر درصد ویتامین «ث» دو درصد کلسیم دو درصد آهن 14 درصد عدس با تمام مزایایی که دارد نباید زیاد مصرف شود و در خوردن آن اسراف گردد زیرا اولا دیر هضم است و تولید نفخ می\u200Cکند ثانیا قابض است و جریان خون را در رگها آهسته می\u200Cکند. البته می\u200Cتوان برای جلوگیری از این عوارض عدس را با روغن کنجد و یا روغن بادام و با سرکه خورد. ضمنا عدس به علت داشتن میکروپروتئین برای افرادی که ناراحتی کلیه دارند، زیانبخش است." );
    user.put("name", " عدس");
        user.put("image", baseImageUrl+"adas.jpg");

//        db.collection("beans_item")
//                .add(user)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Log.d("Adding to Firebase", "DocumentSnapshot added with ID: " + documentReference.getId());
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w("Adding to Firebase", "Error adding document", e);
//                    }
//                });

//
//   user.put("description","نوعی آلو (آلو سیاه) نوید دهنده افزایش ضریب سلامت زنان و به ویژه ساختار استخوانی آنها پس از دوران یائسگی است. پروفسور ارجمندی محقق سرشناس ایرانی دانشگاه فلوریدای آمریکاست که پس از سالها تحقیق و پژوهش درباره تاثیرات این میوه ارزشمند در آستانه ارایه فرمولی جدید و موثر برای کمک به زنان مستعد ابتلا به پوکی و مشکلات استخوانی است. به گزارش مهر، پوکی استخوان بیماری همه گیری است که به تنهایی در آمریکا ۱۰ میلیون را مبتلا کرده است و مهمتر آن است که ۸۰ درصد این جمعیت در معرض خطر را زنان تشکیل می دهند. در کنار مصرف داروهای مختلف، تغذیه می تواند نقش مهمی در سلامت استخوانها و جلوگیری از ابتلا به پوکی استخوان ایجاد کند. این ماموریت علمی و تحقیقاتی است که پروفسور ارجمندی از دانشگاه فلوریدا به مدت ۵ سال است که در دانشگاه اکلاهاما در حال انجام آن است. اکنون جامعه علمی آمریکا و جهان منتظر است تا نتایج تحقیقات این محقق برجسته ایرانی درخصوص تاثیر مثبت و حیرت آور مصرف آلو سیاه بر جلوگیری از ابتلا به پوکی استخوان در تابستان ۲۰۰۸ منتشر شود. تحقیقات صورت گرفته از سوی پروفسور ارجمندی نشان می دهند که آلو سیاه تاثیر فوق العاده مثبتی در تنظیم و مقاوم سازی توده های استخوانی در حیوانات مبتلا به پوکی استخوان داشته است. اکنون پروفسور ارجمندی در حال آزمایش متغیرهای مشابه در پروژه تحقیقاتی خود در دانشگاه فلوریدا است و در آن به بررسی دقیق تاثیر مصرف آلو سیاه در زنانی می پردازد که در فاصله زمانی ۲ تا ۱۰ سال پس از دوران یائسگی خود قرار دارند. این تحقیقات آنچنان از اهمیت برخوردار است که وزارت کشاورزی آمریکا حمایت خود را از آن اعلام کرده و پیش بینی می شود نتایج قطعی آن در تابستان ۲۰۰۸ اعلام شود");
//
//        user.put("image", baseImageUrl+"aloosia.jpg");
//        user.put("name", "آلو سیاه");
//        db.collection("fruit_item")
//                .add(user)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Log.d("Adding to Firebase", "DocumentSnapshot added with ID: " + documentReference.getId());
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w("Adding to Firebase", "Error adding document", e);
//                    }
//                });
//
//        user.put("description","نام علمی Ocimum basilicum گیاه شناسی ریحان گیاهی است علفی یكساله و معطر كه ارتفاع ساقه آن تا 60 سانتیمتر می رسد . برگهای آن بصورت متقابل بیضوی و نوك تیز با كناره های دندانه دار می باشد . گلهای آن معطر و كوچك به رنگهای سفید، قرمز و گاهی بنفش مشاهده می شود . تخم آن سیاه و ریز است . برگ ریحان و سرشاخه های جوان آن بمصرف تغذیه می رسد ریحان در ایران و افغانستان بطور خودرو می روید در اكثر نقاط دنیا كاشته می شود نحوه استفاده : در سبزیجات به کار میرود و در گاهی از اوقات به صورت دم کرده هم کاربرد دارد. خواص داروئی: چون ریحان بسیار معطر است بنابراین برای معطر ساختن غذاها از آن استفاده می شود ریحان از لحاظ قدیم ایران كمی گرم و خشك است . )1دم كرده برگ ریحان اثر ضد تشنج درد مقوی و مدر است . 2)دم كرده برگ ریحان معالج سردردهای میگرن و عصبی است . 3)خوردن ریحان دستگاه هاضمه راتقویت می كند . 4)نوشیدن یك فنجان دم كرده ریحان نفخ و گاز معده را از بین میبرد . 5)برگ ریحان درمان كننده ناراحتی های حاصل از نیش حشرات است كافی است كه محل گزش حشرات را با برگ ریحان ماساژ دهید خارش و درد را برطرف می كند. 6)جویدن برگ ریحان برای زخم های دهان مفید است . 7)جوشانده ریحان تب بر است . 8)دم كرده تخم ریحان مسكن است . 9)اگر تخم ریحان را بكوبید و با صمغ عربی مخلوط كنید داروی خوبی برای درمان اسهال است . 10)تخم ریحان برای رفع دل درد مفید است . 11)برگ ریحان اثر زیاد كننده شیر دارد و مادران شیر ده حتما باید از این گیاه استفاده كنند . 12)دم كرده دانه ریحان برای درمان ورم كلیه و ترشحات زنانه بكار می رود . 13)دم كرده برگ ریحان درمان كننده سرگیجه است . 14)برگ ریحان را همراه غذا بخورید در هضم غذا موثر است . 15)اگر گلدان ریحان را جلوی پنجره بگذارید حشرات و مگس را فراری می دهد و آنها و داخل خانه نخواهند شد .");
//    user.put("image", baseImageUrl+"raihan.jpg");
//        user.put("name", "ریحان");
//        db.collection("vegetable_item")
//                .add(user)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Log.d("Adding to Firebase", "DocumentSnapshot added with ID: " + documentReference.getId());
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w("Adding to Firebase", "Error adding document", e);
//                    }
//                });




    }

    Observer<ArrayList<CategoryModel>> userListUpdateObserver = new Observer<ArrayList<CategoryModel>>() {
        @Override
        public void onChanged(ArrayList<CategoryModel> userArrayList) {
//
//            Collections.reverse(userArrayList);
            adapterCategory.updateUserList(userArrayList);
            MyDialog.dismissDialog();
        }
    };


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}